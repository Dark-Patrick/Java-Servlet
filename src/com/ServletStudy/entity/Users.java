package com.ServletStudy.entity;

public class Users {
    private Integer userId;
    private String userName;
    private String userPwd;
    private String sex;
    private String email;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
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

    public Users(){}

    public Users(Integer userId, String userName, String userPwd, String sex, String email){
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
        this.sex = sex;
        this.email = email;
    }
}
