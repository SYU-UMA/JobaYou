package com.jobayour.modules.qualification;

import javax.persistence.*;

@Entity
@Table(name="qualifications")
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qualificationsnumber")
    private int qualificationsNumber;
    @Column(name = "userid")
    private String userId;
    @Column(name = "job")
    private String job;
    @Column(name = "career")
    private String career;
    @Column(name = "level")
    private String level;
    @Column(name = "requirements")
    private String requirements;

    public Qualification() {
    }

    public Qualification(int qualificationsNumber, String userId, String job, String career, String level, String requirements) {
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

    public void setCareer(String carrer) {
        this.career = carrer;
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
        return "Qualification{" +
                "qualificationsNumber=" + qualificationsNumber +
                ", userId='" + userId + '\'' +
                ", job='" + job + '\'' +
                ", career='" + career + '\'' +
                ", level='" + level + '\'' +
                ", requirements='" + requirements + '\'' +
                '}';
    }
}
