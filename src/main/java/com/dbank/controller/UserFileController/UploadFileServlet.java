package com.dbank.controller.UserFileController;

import com.dbank.domain.UserFile;
import com.dbank.service.UserFileService;
import com.dbank.service.impl.UserFileServiceImpl;
import com.dbank.util.SystemTime;
import com.dbank.util.TransactionHandler;
import com.dbank.util.UUIDGenerator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = { "/file/upload.do" })
public class UploadFileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("text/html;charset=utf-8");
        String fileUUID = UUIDGenerator.generate();
        String userUUID = request.getParameter("userUUID");

        //在这里(接收浏览器提交过来的数据，将数据持久化到硬盘上。)
        //创建一个“硬盘文件条目工厂“对象
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置阈值（默认吞吐量是10240byte：10KB）
        factory.setSizeThreshold(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD); //设置JVM一次能够处理的吞吐量（文件大小：1GB，一次吞吐量：1MB）
        //设置临时文件存储的目录（仓库）
        File tmpDir = new File("E:\\project\\NetDisk\\web\\tmp");
        if(!tmpDir.exists()){
            tmpDir.mkdirs();
        }
        //设置文件上传后保存的目录
        File fileDir = new File("E:\\project\\NetDisk\\web\\files");
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        factory.setRepository(tmpDir);
        //创建ServletFileUpload
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置编码
        upload.setHeaderEncoding("utf-8");
        // 3、判断提交上来的数据是否是上传表单的数据
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 按照传统方式获取数据
            return;
        }
        //处理request对象，获取表单提交的全部内容，将封装的FileItem对象存储进List
        List<FileItem> fileItemList;
        try {
            fileItemList = upload.parseRequest(request);
            for(FileItem fileItem : fileItemList){
                //如果是文件表单字段
                if(!fileItem.isFormField()){
                    String fileName = fileItem.getName();
                    //获取文件名
                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                    //保证文件名唯一
                    //String fileNameUUID = UUID.randomUUID().toString() + "_" + fileName;
                    //文件路径
                    String filePath = fileDir + "\\" + userUUID + "\\" + fileName;
                    File file = new File(filePath);
                    //获取文件大小
                    long temp = fileItem.getSize();
                    String fileSize = temp + "";
                    //设置要限制的文件扩展名
                    String[] suffixes = new String[]{".exe",".bat"};
                    //设置上传文件的最大尺寸为10M
                    long maxSize = 10*1024*1024;
                    //创建文件扩展名过滤器，它可以调用accept()方法检测文件扩展名
                    SuffixFileFilter fileFilter = new SuffixFileFilter(suffixes);
                    //如果文件名以".exe",".bat"结尾
                    if(fileFilter.accept(file)){
                        System.out.println("禁止上传.exe和.bat文件");
                    }else if(fileItem.getSize() > maxSize){
                        System.out.println("文件大小不能超过10M");
                    }else{
                        InputStream inputStream = fileItem.getInputStream();
                        FileOutputStream outputStream = new FileOutputStream(file);
                        byte[] buffer = new byte[1024];
                        int len;
                        while((len = inputStream.read(buffer)) > 0){
                            outputStream.write(buffer,0,len);
                        }
                        //关流
                        inputStream.close();
                        outputStream.close();
                        // 删除处理文件上传时生成的临时文件
                        fileItem.delete();
                        System.out.println("已成功上传文件：" + fileName);

                        //将文件信息存入数据库
                        UserFile userFile = new UserFile();
                        userFile.setFileUUID(fileUUID);
                        userFile.setFileName(fileName);
                        userFile.setFileSize(fileSize);
                        userFile.setFilePath(filePath);
                        userFile.setUploadTime(SystemTime.getTime());
                        userFile.setIsFolder("false");
                        userFile.setIsChecked("false");
                        userFile.setUid(userUUID);

                        UserFileService userFileService = (UserFileService) new TransactionHandler(new UserFileServiceImpl()).getProxy();
                        int saveSuccess = userFileService.uploadFile(userFile);
                        //UserFileService userFileService = new UserFileService();
                        //UserFile userFile = new UserFile(null,fileNameUUID,filePath,fileSize,uploadTime,"No",null);
                        //int result = userFileService.insertFile(userFile);
                        if(saveSuccess == 1){
                            //保存成功
                            response.getWriter().print("{\"saveSuccess\" : " + saveSuccess + "}");
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

    }
}
