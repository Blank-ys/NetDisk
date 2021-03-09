package com.dbank.controller.UserFileController;

import com.dbank.service.UserFileService;
import com.dbank.service.impl.UserFileServiceImpl;
import com.dbank.util.OperateFile;
import com.dbank.util.TransactionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = { "/pathsController/delFile" })
public class DeleteFilesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String fileUUID = request.getParameter("fileUUID");

        UserFileService userFileService = (UserFileService) new TransactionHandler(new UserFileServiceImpl()).getProxy();
        userFileService.delFilesByFileUUID(fileUUID);
        String fileName = userFileService.getFileNameByFileUUID(fileUUID);
        String filePath = userFileService.getFilePathByFileUUID(fileUUID);
        //删除服务器中的文件
        OperateFile.deleteDirOrFile(new File(filePath + "\\" + fileName));

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("删除成功");
    }
}
