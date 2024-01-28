package com.jobayour.modules.crawling;

import java.io.Serializable;

public class JobData implements Serializable {

    private String companyName;
    private String companyUrl;
    private String title;
    private String url;
    private String info;
    private String description;

    public JobData() {
    }

    public JobData(String companyName, String companyUrl, String title, String url, String info, String description) {
        this.companyName = companyName;
        this.companyUrl = companyUrl;
        this.title = title;
        this.url = url;
        this.info = info;
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "JobData{" +
                "companyName='" + companyName + '\'' +
                ", companyUrl='" + companyUrl + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", info='" + info + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
