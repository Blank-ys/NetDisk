package com.dbank.domain;

public class UserFile {
    private String fileUUID;
    private String fileName;
    private String fileSize;
    private String filePath;
    private String uploadTime;
    private String isFolder;
    private String isChecked;
    private String uid;

    public UserFile() {
    }

    public UserFile(String fileUUID, String fileName, String fileSize, String filePath, String uploadTime, String isFolder, String isChecked, String uid) {
        this.fileUUID = fileUUID;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.uploadTime = uploadTime;
        this.isFolder = isFolder;
        this.isChecked = isChecked;
        this.uid = uid;
    }

    public String getFileUUID() {
        return fileUUID;
    }

    public void setFileUUID(String fileUUID) {
        this.fileUUID = fileUUID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(String isFolder) {
        this.isFolder = isFolder;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
