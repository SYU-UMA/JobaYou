package com.jobayour.modules.interview;

import javax.persistence.*;

@Entity
@Table(name = "interview")
public class Interview {
    //수정
    @Id
    @Column(name = "interviewnumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int interviewNumber;
    @Column(name = "qualificationsnumber")
    private int qualificationsNumber;
    @Column(name = "userid")
    private String userId;
    @Column(name = "job")
    private String job;
    @Column(name = "resumenum")
    private int resumeNum;
    @Column(name = "interviewquestion")
    private String interviewQuestion;
    @Column(name = "interviewanswer")
    private String interviewAnswer;

    public Interview() {
    }

    public Interview(int interviewNumber, int qualificationsNumber, String userId, String interviewQuestion, String interviewAnswer) {
        this.interviewNumber = interviewNumber;
        this.qualificationsNumber = qualificationsNumber;
        this.userId = userId;
        this.interviewQuestion = interviewQuestion;
        this.interviewAnswer = interviewAnswer;
    }


    public Interview(int interviewNumber, int qualificationsNumber, String userId, String job, int resumeNum, String interviewQuestion, String interviewAnswer) {
        this.interviewNumber = interviewNumber;
        this.qualificationsNumber = qualificationsNumber;
        this.userId = userId;
        this.job = job;
        this.resumeNum = resumeNum;
        this.interviewQuestion = interviewQuestion;
        this.interviewAnswer = interviewAnswer;
    }

    //이력서 넣고 gpt 검색할 경우
    public Interview(int qualificationsNumber, String userId, String job, int resumeNum, String interviewQuestion, String interviewAnswer) {
        this.qualificationsNumber = qualificationsNumber;
        this.userId = userId;
        this.job = job;
        this.resumeNum = resumeNum;
        this.interviewQuestion = interviewQuestion;
        this.interviewAnswer = interviewAnswer;
    }

    //이력서 없이 gpt 검색할 경우


    public Interview(int qualificationsNumber, String userId, String job, String interviewQuestion, String interviewAnswer) {
        this.qualificationsNumber = qualificationsNumber;
        this.userId = userId;
        this.job = job;
        this.interviewQuestion = interviewQuestion;
        this.interviewAnswer = interviewAnswer;
    }

    public int getInterviewNumber() {
        return interviewNumber;
    }

    public void setInterviewNumber(int interviewNumber) {
        this.interviewNumber = interviewNumber;
    }

    public int getQualificationsNumber() {
        return qualificationsNumber;
    }

    public void setQualificationsNumber(int qualificationsNumber) {
        this.qualificationsNumber = qualificationsNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getResumeNum() {
        return resumeNum;
    }

    public void setResumeNum(int resumeNum) {
        this.resumeNum = resumeNum;
    }

    public String getInterviewQuestion() {
        return interviewQuestion;
    }

    public void setInterviewQuestion(String interviewQuestion) {
        this.interviewQuestion = interviewQuestion;
    }

    public String getInterviewAnswer() {
        return interviewAnswer;
    }

    public void setInterviewAnswer(String interviewAnswer) {
        this.interviewAnswer = interviewAnswer;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "interviewNumber=" + interviewNumber +
                ", qualificationsNumber=" + qualificationsNumber +
                ", userId='" + userId + '\'' +
                ", job='" + job + '\'' +
                ", resumeNum='" + resumeNum + '\'' +
                ", interviewQuestion='" + interviewQuestion + '\'' +
                ", interviewAnswer='" + interviewAnswer + '\'' +
                '}';
    }
}