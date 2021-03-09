package com.dbank.controller.UserController;

import com.dbank.domain.User;
import com.dbank.domain.UserFile;
import com.dbank.service.UserFileService;
import com.dbank.service.UserService;
import com.dbank.service.impl.UserFileServiceImpl;
import com.dbank.service.impl.UserServiceImpl;
import com.dbank.util.OperateFile;
import com.dbank.util.SystemTime;
import com.dbank.util.TransactionHandler;
import com.dbank.util.UUIDGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = { "/userController/register" })
public class UserRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //接收参数
        String userUUID = UUIDGenerator.generate();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");
        String registerTime = SystemTime.getTime();
        String identity = "user";
        //封装数据
        User user = new User(userUUID,userName,password,sex,email,registerTime,identity);
        //调用service
        UserService userService = (UserService) new TransactionHandler(new UserServiceImpl()).getProxy();
        boolean loginSuccess = userService.registerUser(user);
        //如果注册成功，则给用户创建一个初始文件夹（以userName命名）
        if(loginSuccess){
            //在服务器创建文件夹
            OperateFile.mkPersonalDir(userName);
            //将文件数据传入数据库
            String filePath = OperateFile.USER_FILE_PATH + "\\" + userName;
            UserFile userFile = new UserFile(UUIDGenerator.generate(),
                    userName,null,filePath, SystemTime.getTime(),"true","true",userUUID);
            UserFileService userFileService = (UserFileService) new TransactionHandler(new UserFileServiceImpl()).getProxy();
            userFileService.createFolder(userFile);
            //响应json
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("");
        }

    }
}
