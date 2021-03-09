package com.dbank.controller.UserFileController;

import com.dbank.service.UserFileService;
import com.dbank.service.impl.UserFileServiceImpl;
import com.dbank.util.TransactionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "/pathsController/renameFile" })
public class RenameFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String fileUUID = request.getParameter("fileUUID");
        String fileName = request.getParameter("fileName");
        String filePath = request.getParameter("filePath");

        UserFileService userFileService = (UserFileService) new TransactionHandler(new UserFileServiceImpl()).getProxy();
        boolean rename = userFileService.updateFileByFileUUID(fileName,filePath,fileUUID);

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("{\"renameFile\" : " + rename + "}");
    }
}
