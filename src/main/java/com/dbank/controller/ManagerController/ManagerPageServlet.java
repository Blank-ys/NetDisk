package com.dbank.controller.ManagerController;

import com.dbank.domain.User;
import com.dbank.domain.UserFile;
import com.dbank.service.UserFileService;
import com.dbank.service.UserService;
import com.dbank.service.impl.UserFileServiceImpl;
import com.dbank.service.impl.UserServiceImpl;
import com.dbank.util.OperateFile;
import com.dbank.util.TransactionHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = { "/manager/userPage.do", "/userController/delUser", "/manager/filePage.do", "/userController/getUsers","/checkController/getFiles", "/checkController/checkFiles" })
public class ManagerPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("text/html;charset=utf-8");
        String servletPath = request.getServletPath();
        if("/manager/userPage.do".equals(servletPath)){ //分页条件查询用户
            listUsersByCondition(request,response);
        }else if("/userController/delUser".equals(servletPath)){ //删除用户
            deleteUserAndFiles(request,response);
        }else if("/manager/filePage.do".equals(servletPath)){ //分页条件查询文件
            listFilesByCondition(request,response);
        }else if("/userController/getUsers".equals(servletPath)){ //查询用户列表
            doUserList(request,response);
        }else if("/checkController/getFiles".equals(servletPath)){ //查询文件列表
            doFileList(request,response);
        }else if("/checkController/checkFiles".equals(servletPath)){ //审核文件
            doCheckFile(request,response);
        }
    }

    public void doCheckFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileUUID = request.getParameter("fileUUID");
        String isChecked = request.getParameter("isChecked");
        //修改UserFile表中的isChecked属性，表示该文件已被审核
        UserFileService userFileService = (UserFileService) new TransactionHandler(new UserFileServiceImpl()).getProxy();
        boolean check = userFileService.checkFile(isChecked,fileUUID);
        if(check){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("checked success!");
        }
    }

    public void doFileList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserFileService userFileService = (UserFileService) new TransactionHandler(new UserFileServiceImpl()).getProxy();
        List<UserFile> userFileList = userFileService.getAllFiles();

        response.setContentType("text/html;charset=utf-8");
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(userFileList);
        response.getWriter().println(json);
    }

    public void listFilesByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        String userUUID = request.getParameter("userUUID");
        String fileUUID = request.getParameter("fileUUID");

        Map<String,Object> conditionMap = new HashMap<>();
        conditionMap.put("startIndex", (pageNo - 1) * pageSize);
        conditionMap.put("pageSize", pageSize);
        conditionMap.put("userUUID",userUUID);
        conditionMap.put("fileUUID",fileUUID);

        UserFileService userFileService = (UserFileService) new TransactionHandler(new UserFileServiceImpl()).getProxy();
        Map<String,Object> pageMap = userFileService.getPageByCondition(conditionMap);

        response.setContentType("text/html;charset=utf-8");
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(pageMap);
        response.getWriter().println(json);
    }

    public void doUserList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService userService = (UserService) new TransactionHandler(new UserServiceImpl()).getProxy();
        List<User> userList = userService.getAllUsers();

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(userList);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(json);
    }

    public void deleteUserAndFiles(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userUUID = request.getParameter("userUUID");

        UserFileService userFileService = (UserFileService) new TransactionHandler(new UserFileServiceImpl()).getProxy();
        userFileService.delFilesByUserUUID(userUUID);

        UserService userService = (UserService) new TransactionHandler(new UserServiceImpl()).getProxy();
        String userName = userService.getUserNameByUserUUID(userUUID);
        //在服务器中删除该用户所有文件
        OperateFile.deleteDirOrFile(new File(OperateFile.USER_FILE_PATH + "\\" + userName));
        boolean deleteUser = userService.deleteUserByUserUUID(userUUID);

        if(deleteUser){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("");
        }
    }

    public void listUsersByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        String userName = request.getParameter("userName");
        String sex = request.getParameter("sex");
        String registerTime = request.getParameter("registerTime");

        Map<String,Object> conditionMap = new HashMap<>();
        conditionMap.put("startIndex", (pageNo - 1) * pageSize);
        conditionMap.put("pageSize", pageSize);
        conditionMap.put("userName", userName);
        conditionMap.put("sex", sex);
        conditionMap.put("registerTime", registerTime);

        UserService userService = (UserService) new TransactionHandler(new UserServiceImpl()).getProxy();
        Map<String,Object> pageMap = userService.getPageByCondition(conditionMap);

        response.setContentType("text/html;charset=utf-8");
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(pageMap);
        response.getWriter().println(json);
    }
}
