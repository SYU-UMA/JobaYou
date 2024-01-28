package com.jobayour.modules.resumefunc.resumeBasic;


import java.io.Serializable;

public class ResumeBasicDTO implements Serializable {

    private int resumeNum;
    private String userId;
    private String career;

    private String resumeName;
    private String userAddress;

    public ResumeBasicDTO() {
    }

    public ResumeBasicDTO(int resumeNum, String userId, String career, String resumeName, String userAddress) {
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
        return "ResumeBasicDTO{" +
                "resumeNum=" + resumeNum +
                ", userId='" + userId + '\'' +
                ", career='" + career + '\'' +
                ", resumeName='" + resumeName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}
