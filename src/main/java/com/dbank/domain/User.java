package com.dbank.domain;

public class User {
    private String userUUID;
    private String userName;
    private String password;
    private String sex;
    private String email;
    private String registerTime;
    private String identity = "user";

    public User() {
    }

    public User(String userUUID, String userName, String password, String sex, String email, String registerTime, String identity) {
        this.userUUID = userUUID;
        this.userName = userName;
        this.password = password;
        this.sex = sex;
        this.email = email;
        this.registerTime = registerTime;
        this.identity = identity;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
