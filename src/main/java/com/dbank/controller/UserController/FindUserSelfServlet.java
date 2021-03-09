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
import java.io.IOException;

@WebServlet(urlPatterns = { "/userController/getUser" })
public class FindUserSelfServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("text/html;charset=utf-8");
        String userUUID = request.getParameter("userUUID");

        UserService userService = (UserService) new TransactionHandler(new UserServiceImpl()).getProxy();
        User user = userService.getUserByUserUUID(userUUID);

        response.setContentType("text/html;charset=utf-8");
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(user);
        response.getWriter().print(json);
    }
}
