package com.example.demo.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String userName;
    private String userPwd;
    private Date version;
    private List<Role> roles;

    public User() {
    }

    public User(Integer userId, String userName, String userPwd, Date version, List<Role> roles) {
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
        this.version = version;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", version=" + version +
                ", roles=" + roles +
                '}';
    }

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

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
