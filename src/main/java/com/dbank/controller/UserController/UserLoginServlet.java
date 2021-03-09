package com.dbank.controller.UserController;

import com.dbank.domain.User;
import com.dbank.service.UserService;
import com.dbank.service.impl.UserServiceImpl;
import com.dbank.util.TransactionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = { "/userController/login" })
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String identity;
        if("admin".equals(userName)){
            identity = "manager";
        }else{
            identity = "user";
        }

        UserService userService = (UserService) new TransactionHandler(new UserServiceImpl()).getProxy();
        User user = userService.userLoginByUserNameAndPassword(userName,password,identity);

        response.setContentType("text/html;charset=utf-8");
        if(user != null){
            //登陆成功，返回用户对象的json格式
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(user);
            response.getWriter().print(json);
        }
    }
}
