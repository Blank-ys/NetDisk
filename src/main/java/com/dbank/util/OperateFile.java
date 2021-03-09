package com.dbank.util;

import java.io.File;

public class OperateFile {

    public static final String USER_FILE_PATH = "E:\\project\\NetDisk\\web\\files"; //网盘存储文件根路径

    /**
     *递归删除文件夹和文件夹下的子文件
     * @param dir
     * @return
     */
    public static boolean deleteDirOrFile(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDirOrFile(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * 为每个用户创建一个都以用户的userName命令的目录（用户注册之后给用户一个存储空间）
     * @param userName
     */
    public static void mkPersonalDir(String userName){
        String filePath = USER_FILE_PATH + "\\" + userName;
        File file=new File(filePath);
        if(!file.exists()){//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
    }

    /**
     * 创建一个子目录(创建文件夹)
     * @param filePath
     * @param fileName
     */
    public static void mkChildDir(String filePath,String fileName){
        String path = filePath + "\\" + fileName;
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
    }

    /**
     * 重命名文件
     *      注意：更名文件的前面的父路径必须相同，即文件名之前的都要相同
     * @param prePath   原文件路径
     * @param nowPath   更改后路径
     */
    /*public static void rename(String prePath,String nowPath){
        File file = new File(prePath);
        file.renameTo(new File(nowPath));
    }*/
}
