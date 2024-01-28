package com.jobayour.modules.resumefunc.career;

import javax.persistence.*;
import java.sql.Date;

/* 경력 엔티티 */
@Entity
@Table(name = "career")
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "careernum")
    private int careerNum;
    @Column(name = "userid")
    private String userId;
    @Column(name = "resumenum")
    private int resumeNum;
    @Column(name = "companyname")
    private String companyName;
    @Column(name = "retire")
    private String retire;
    @Column(name = "careerstart")
    private String careerStart;
    @Column(name = "careerend")
    private String careerEnd;
    @Column(name = "jobgradeduties")
    private String jobGradeDuties;
    @Column(name = "contents")
    private String contents;
    @Column(name = "deptname")
    private String deptName;
    @Column(name = "salary")
    private int salary;
    @Column(name = "currency")
    private String currency;
    @Column(name = "area")
    private String area;

    public Career() {
    }

    public Career(int careerNum, String userId, int resumeNum, String companyName, String retire, String careerStart, String careerEnd, String jobGradeDuties, String contents, String deptName, int salary, String currency, String area) {
        this.careerNum = careerNum;
        this.userId = userId;
        this.resumeNum = resumeNum;
        this.companyName = companyName;
        this.retire = retire;
        this.careerStart = careerStart;
        this.careerEnd = careerEnd;
        this.jobGradeDuties = jobGradeDuties;
        this.contents = contents;
        this.deptName = deptName;
        this.salary = salary;
        this.currency = currency;
        this.area = area;
    }

    public int getCareerNum() {
        return careerNum;
    }

    public void setCareerNum(int careerNum) {
        this.careerNum = careerNum;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRetire() {
        return retire;
    }

    public void setRetire(String retire) {
        this.retire = retire;
    }

    public String getCareerStart() {
        return careerStart;
    }

    public void setCareerStart(String careerStart) {
        this.careerStart = careerStart;
    }

    public String getCareerEnd() {
        return careerEnd;
    }

    public void setCareerEnd(String careerEnd) {
        this.careerEnd = careerEnd;
    }

    public String getJobGradeDuties() {
        return jobGradeDuties;
    }

    public void setJobGradeDuties(String jobGradeDuties) {
        this.jobGradeDuties = jobGradeDuties;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Career{" +
                "CareerNum=" + careerNum +
                ", userId='" + userId + '\'' +
                ", resumeNum=" + resumeNum +
                ", companyName='" + companyName + '\'' +
                ", retire='" + retire + '\'' +
                ", careerStart=" + careerStart +
                ", careerEnd=" + careerEnd +
                ", jobGradeDuties='" + jobGradeDuties + '\'' +
                ", contents='" + contents + '\'' +
                ", deptName='" + deptName + '\'' +
                ", salary=" + salary +
                ", currency='" + currency + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
