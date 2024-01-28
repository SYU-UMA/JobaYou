package com.jobayour.modules.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/*2023-12-01 주석
 유저 프로필 엔티티
 생년월일 : DATE 형식 ex) 2020-01-01 이런방식으로 저장
 */
@Entity
@Table(name="userprofiles")
public class User{
    @Id
    @Column(name = "userid")
    private String userId;

    @Column(name = "userpwd")
    private String userPwd;

    @Column(name = "username")
    private String userName;
    @Column(name = "userbirthday")
    private String userBirthday;
    @Column(name = "userphone")
    private String userPhone;
    @Column(name = "useremail")
    private String userEmail;

    public User() {
    }

    public User(String userId, String userPwd, String userName, String userBirthday, String userPhone, String userEmail) {
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
        return "User{" +
                "userId='" + userId + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userName='" + userName + '\'' +
                ", userBirthday=" + userBirthday +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
