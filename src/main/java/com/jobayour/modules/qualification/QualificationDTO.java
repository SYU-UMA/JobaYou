package com.jobayour.modules.qualification;

import java.io.Serializable;

public class QualificationDTO implements Serializable {

    private int qualificationsNumber;
    private String userId;
    private String job;
    private String career;
    private String level;
    private String requirements;

    public QualificationDTO() {
    }

    public QualificationDTO(int qualificationsNumber, String userId, String job, String career, String level, String requirements) {
        this.qualificationsNumber = qualificationsNumber;
        this.userId = userId;
        this.job = job;
        this.career = career;
        this.level = level;
        this.requirements = requirements;
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

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    @Override
    public String toString() {
        return "QualificationDTO{" +
                "qualificationsNumber=" + qualificationsNumber +
                ", userId='" + userId + '\'' +
                ", job='" + job + '\'' +
                ", career='" + career + '\'' +
                ", level='" + level + '\'' +
                ", requirements='" + requirements + '\'' +
                '}';
    }
}
