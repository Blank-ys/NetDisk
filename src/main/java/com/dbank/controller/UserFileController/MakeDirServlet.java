package com.dbank.controller.UserFileController;

import com.dbank.domain.UserFile;
import com.dbank.service.UserFileService;
import com.dbank.service.impl.UserFileServiceImpl;
import com.dbank.util.OperateFile;
import com.dbank.util.SystemTime;
import com.dbank.util.TransactionHandler;
import com.dbank.util.UUIDGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "/pathsController/getFiles" })
public class MakeDirServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String userUUID = request.getParameter("userUUID");
        //获得创建的文件夹名
        String fileName = request.getParameter("dir");
        //在当前路径下创建
        String filePath= request.getParameter("filePath");
        //在服务器中创建文件夹
        OperateFile.mkChildDir(filePath,fileName);
        //封装数据
        UserFile userFile = new UserFile(UUIDGenerator.generate(),
                fileName,null,filePath, SystemTime.getTime(),"true","true",userUUID);
        //传给数据库
        UserFileService userFileService = (UserFileService) new TransactionHandler(new UserFileServiceImpl()).getProxy();
        userFileService.createFolder(userFile);
        //响应
        response.getWriter().print("");
    }
}
