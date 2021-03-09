package com.dbank.dao;

import com.dbank.domain.User;
import com.dbank.domain.UserFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserFileDao {

    /**
     * 新增文件
     * @return
     */
    int addFile(UserFile userFile);

    /**
     * 根据uid删除文件（删除用户时使用）
     * @return
     */
    void deleteFileByUid(String uid);

    /**
     * 根据filePath删除文件
     * @param fileUUID
     * @return
     */
    void deleteFileByFileUUID(String fileUUID);

    /**
     * 得到所有文件列表
     * @return
     */
    List<UserFile> selectAllFiles();

    /**
     *
     * @param uid
     * @return
     */
    List<UserFile> getAllFilesByUid(String uid);

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
     * @param fileName
     * @return
     */
    String getFileNameByFileUUID(String fileName);

    /**
     * 用户修改文件名，数据库根据fileUUID修改文件名和文件路径
     * @param fileName
     * @param filePath
     * @param fileUUID
     * @return
     */
    int updateFileByFileUUID(String fileName,String filePath,String fileUUID);

    /**
     * 修改文件是否有效
     * @param isChecked
     * @param fileUUID
     * @return
     */
    int updateIsChecked(String isChecked,String fileUUID);

    /**
     * 得到符合查询条件的总页数
     * @param conditionMap
     * @return
     */
    Long getTotalByCondition(Map<String, Object> conditionMap);

    /**
     * 得到符合查询条件的文件信息列表
     * @param conditionMap
     * @return
     */
    List<UserFile> getFileListByCondition(Map<String, Object> conditionMap);
}
