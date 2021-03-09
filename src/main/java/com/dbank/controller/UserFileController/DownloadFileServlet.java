package com.dbank.controller.UserFileController;

import com.dbank.domain.User;
import com.dbank.service.UserFileService;
import com.dbank.service.impl.UserFileServiceImpl;
import com.dbank.util.TransactionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@WebServlet(urlPatterns = { "/pathsController/download" })
public class DownloadFileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("text/html;charset=utf-8");
        //HttpSession session = request.getSession();
        //User user = (User) session.getAttribute("user");
        String fileUUID = request.getParameter("fileUUID");

        UserFileService userFileService = (UserFileService) new TransactionHandler(new UserFileServiceImpl()).getProxy();
        //获取文件路径
        String filePath = userFileService.getFilePathByFileUUID(fileUUID);
        // 得到要下载的文件名
        String fileName = userFileService.getFileNameByFileUUID(fileUUID);

        // 上传的文件都是保存在E:\project\NetDisk\web\files目录下的子目录当中
        //String fileSaveRootPath = "E:\\project\\NetDisk\\web\\files" + userId;
        // 通过文件名找出文件的所在目录
        String path = filePath + "\\" + fileName;
        // 得到要下载的文件
        File file = new File(path);
        // 如果文件不存在
        if (!file.exists()) {
            request.setAttribute("message", "您要下载的资源已被删除！");
            return;
        }
        // 处理文件名
        //String realname = fileName.substring(fileName.indexOf("_") + 1);
        //response.setHeader("content-disposition", "attachment;filename="
        //                + URLEncoder.encode(realname, "UTF-8"));
        // 设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename="
                + URLEncoder.encode(fileName, "UTF-8"));
        // 读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(path + "\\" + fileName);
        // 创建输出流
        OutputStream out = response.getOutputStream();
        // 创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        // 循环将输入流中的内容读取到缓冲区当中
        while ((len = in.read(buffer)) > 0) {
            // 输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        // 关闭文件输入流
        in.close();
        // 关闭输出流
        out.close();
    }
}
