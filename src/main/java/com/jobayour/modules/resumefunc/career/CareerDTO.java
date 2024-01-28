package com.jobayour.modules.resumefunc.career;

import java.io.Serializable;
import java.sql.Date;

public class CareerDTO implements Serializable {

    private int careerNum;
    private String userId;
    private int resumeNum;
    private String companyName;
    private String retire;
    private String careerStart;
    private String careerEnd;
    private String jobGradeDuties;
    private String contents;
    private String deptName;
    private int salary;
    private String currency;
    private String area;

    public CareerDTO() {
    }

    public CareerDTO(int careerNum, String userId, int resumeNum, String companyName, String retire, String careerStart, String careerEnd, String jobGradeDuties, String contents, String deptName, int salary, String currency, String area) {
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
        return "CareerDTO{" +
                "careerNum=" + careerNum +
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
