package com.jobayour.modules.resumefunc.resumeBasic;

import javax.persistence.*;

/* 2023-12-01
*  이력서 엔티티*/
@Entity
@Table(name = "resumebasic")
public class ResumeBasic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resumenum")
    private int resumeNum;

    @Column(name = "userid")
    private String userId;

    @Column(name = "career")
    private String career;

    @Column(name = "resumename")
    private String resumeName;
    @Column(name = "useraddress")
    private String userAddress;
    public ResumeBasic() {
    }

    public ResumeBasic(int resumeNum, String userId, String career, String resumeName, String userAddress) {
        this.resumeNum = resumeNum;
        this.userId = userId;
        this.career = career;
        this.resumeName = resumeName;
        this.userAddress = userAddress;
    }

    public int getResumeNum() {
        return resumeNum;
    }

    public void setResumeNum(int resumeNum) {
        this.resumeNum = resumeNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "ResumeBasic{" +
                "resumeNum=" + resumeNum +
                ", userId='" + userId + '\'' +
                ", career='" + career + '\'' +
                ", resumeName='" + resumeName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}
