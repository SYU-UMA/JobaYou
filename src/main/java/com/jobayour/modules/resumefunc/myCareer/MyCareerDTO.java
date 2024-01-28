package com.jobayour.modules.resumefunc.myCareer;

import java.io.Serializable;

public class MyCareerDTO implements Serializable {

    private int myCareerNum;
    private String userId;
    private int resumeNum;
    private String job;
    private String introduction;
    private String coreCompetence1;
    private String coreCompetence2;
    private String coreCompetence3;
    private String coreCompetence4;
    private String coreCompetence5;
    private String url;

    public MyCareerDTO() {
    }

    public MyCareerDTO(int myCareerNum, String userId, int resumeNum, String job, String introduction, String coreCompetence1, String coreCompetence2, String coreCompetence3, String coreCompetence4, String coreCompetence5, String url) {
        this.myCareerNum = myCareerNum;
        this.userId = userId;
        this.resumeNum = resumeNum;
        this.job = job;
        this.introduction = introduction;
        this.coreCompetence1 = coreCompetence1;
        this.coreCompetence2 = coreCompetence2;
        this.coreCompetence3 = coreCompetence3;
        this.coreCompetence4 = coreCompetence4;
        this.coreCompetence5 = coreCompetence5;
        this.url = url;
    }

    public int getMyCareerNum() {
        return myCareerNum;
    }

    public void setMyCareerNum(int myCareerNum) {
        this.myCareerNum = myCareerNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getResumeNum() {
        return resumeNum;
    }

    public void setResumeNum(int resumeNum) {
        this.resumeNum = resumeNum;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCoreCompetence1() {
        return coreCompetence1;
    }

    public void setCoreCompetence1(String coreCompetence1) {
        this.coreCompetence1 = coreCompetence1;
    }

    public String getCoreCompetence2() {
        return coreCompetence2;
    }

    public void setCoreCompetence2(String coreCompetence2) {
        this.coreCompetence2 = coreCompetence2;
    }

    public String getCoreCompetence3() {
        return coreCompetence3;
    }

    public void setCoreCompetence3(String coreCompetence3) {
        this.coreCompetence3 = coreCompetence3;
    }

    public String getCoreCompetence4() {
        return coreCompetence4;
    }

    public void setCoreCompetence4(String coreCompetence4) {
        this.coreCompetence4 = coreCompetence4;
    }

    public String getCoreCompetence5() {
        return coreCompetence5;
    }

    public void setCoreCompetence5(String coreCompetence5) {
        this.coreCompetence5 = coreCompetence5;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "myCareerDTO{" +
                "myCareerNum=" + myCareerNum +
                ", userId='" + userId + '\'' +
                ", resumeNum=" + resumeNum +
                ", job='" + job + '\'' +
                ", introduction='" + introduction + '\'' +
                ", coreCompetence1='" + coreCompetence1 + '\'' +
                ", coreCompetence2='" + coreCompetence2 + '\'' +
                ", coreCompetence3='" + coreCompetence3 + '\'' +
                ", coreCompetence4='" + coreCompetence4 + '\'' +
                ", coreCompetence5='" + coreCompetence5 + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
