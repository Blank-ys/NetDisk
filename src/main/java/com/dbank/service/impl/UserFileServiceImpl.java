package com.dbank.service.impl;

import com.dbank.dao.UserDao;
import com.dbank.dao.UserFileDao;
import com.dbank.domain.UserFile;
import com.dbank.service.UserFileService;
import com.dbank.util.OperateFile;
import com.dbank.util.SqlSessionUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserFileServiceImpl implements UserFileService {

    private UserFileDao userFileDao = SqlSessionUtil.getCurrentSqlSession().getMapper(UserFileDao.class);

    @Override
    public int uploadFile(UserFile userFile) {
        return userFileDao.addFile(userFile);
    }

    @Override
    public void delFilesByFileUUID(String fileUUID) {
        userFileDao.deleteFileByFileUUID(fileUUID);
    }

    @Override
    public void delFilesByUserUUID(String userUUID) {
        userFileDao.deleteFileByUid(userUUID);
    }

    @Override
    public List<UserFile> getAllFiles() {
        return userFileDao.selectAllFiles();
    }

    @Override
    public List<UserFile> getAllFilesByUserUUID(String userUUID) {
        return userFileDao.getAllFilesByUid(userUUID);
    }

    @Override
    public UserFile getFileByFileUUID(String fileUUID) {
        return userFileDao.getFileByFileUUID(fileUUID);
    }

    @Override
    public String getFilePathByFileUUID(String fileUUID) {
        return userFileDao.getFilePathByFileUUID(fileUUID);
    }

    @Override
    public String getFileNameByFileUUID(String fileUUID) {
        return userFileDao.getFileNameByFileUUID(fileUUID);
    }

    @Override
    public boolean updateFileByFileUUID(String fileName, String filePath, String fileUUID) {
        return userFileDao.updateFileByFileUUID(fileName,filePath,fileUUID) == 1;
    }

    @Override
    public boolean checkFile(String isChecked, String fileUUID) {
        return userFileDao.updateIsChecked(isChecked,fileUUID) == 1;
    }

    @Override
    public Map<String, Object> getPageByCondition(Map<String, Object> conditionMap) {
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("total", userFileDao.getTotalByCondition(conditionMap));
        pageMap.put("fileList", userFileDao.getFileListByCondition(conditionMap));
        return pageMap;
    }

    @Override
    public void createFolder(UserFile userFile) {
        userFileDao.addFile(userFile);
    }
}
