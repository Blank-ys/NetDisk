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

@WebServlet(urlPatterns = { "/userController/modUser"})
public class UserUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //HttpSession session = request.getSession();
        //User preUser = (User) session.getAttribute("user");
        String userUUID = request.getParameter("userUUID");

        UserService userService = (UserService) new TransactionHandler(new UserServiceImpl()).getProxy();
        User preUser = userService.getUserByUserUUID(userUUID);
        int result = userService.updateUserByUserUUIDAndReturn(preUser);
        if(result == 1){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("");
        }
    }
}
