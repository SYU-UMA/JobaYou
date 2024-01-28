package com.jobayour.modules.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;


public class UserDTO implements Serializable {

    private String userId;
    private String userPwd;
    private String userName;
    private String userBirthday;
    private String userPhone;
    private String userEmail;

    public UserDTO() {
    }

    public UserDTO(String userId, String userPwd, String userName, String userBirthday, String userPhone, String userEmail) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userBirthday = userBirthday;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId='" + userId + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userName='" + userName + '\'' +
                ", userBirthday=" + userBirthday +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
