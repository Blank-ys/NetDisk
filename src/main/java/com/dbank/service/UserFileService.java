package com.dbank.service;

import com.dbank.domain.User;
import com.dbank.domain.UserFile;

import java.util.List;
import java.util.Map;

public interface UserFileService {

    /**
     * 保存上传的文件
     * @param userFile
     * @return
     */
    int uploadFile(UserFile userFile);

    /**
     * 根据fileUUID删除文件
     * @param fileUUID
     * @return
     */
    void delFilesByFileUUID(String fileUUID);

    /**
     * 删除用户id为userUUID的用户的所有文件（删除用户时使用）
     * @param userUUID
     * @return
     */
    void delFilesByUserUUID(String userUUID);

    /**
     * 查找所有文件信息
     * @return
     */
    List<UserFile> getAllFiles();

    /**
     *用户查询个人文件列表
     * @param   userUUID
     * @return
     */
    List<UserFile> getAllFilesByUserUUID(String userUUID);

    /**
     *
     * @param fileUUID
     * @return
     */
    UserFile getFileByFileUUID(String fileUUID);

    /**
     *
     * @param fileUUID
     * @return
     */
    String getFilePathByFileUUID(String fileUUID);

    /**
     *
     * @param fileUUID
     * @return
     */
    String getFileNameByFileUUID(String fileUUID);

    /**
     * 用户根据fileUUID修改文件信息
     * @param fileName
     * @param filePath
     * @param fileUUID
     * @return
     */
    boolean updateFileByFileUUID(String fileName, String filePath,String fileUUID);

    /**
     * 管理员审核文件并修改
     * @param isChecked
     * @param fileUUID
     * @return
     */
    boolean checkFile(String isChecked,String fileUUID);

    /**
     * 通过条件查询文件信息(分页)
     * @param conditionMap
     * @return
     */
    Map<String, Object> getPageByCondition(Map<String, Object> conditionMap);

    /**
     * 创建用户文件夹
     * @param userFile
     * @return
     */
    void createFolder(UserFile userFile);
}
