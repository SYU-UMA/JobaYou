package com.jobayour.modules.crawling;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class CrawlingService {
    public ArrayList<JobData> getJobData() throws IOException {

        // 웹 페이지를 JSoup을 사용하여 가져오기
        String url = "https://www.jobkorea.co.kr/recruit/joblist";

        Document doc = Jsoup.connect(url).get();


        Elements jobListElements = doc.select("#dev-gi-list > div > div.tplList.tplJobList");
        Elements jobElements = doc.select("#dev-gi-list > div > div.tplList.tplJobList > table > tbody > tr");

        ArrayList<JobData> jobData = new ArrayList<>();
        // 각 job 엘리먼트에 대해 반복합니다.
        for (Element jobElement : jobElements) {

            // 각 열에서 기업 정보를 가져옵니다.
            Element companyLinkElement = jobElement.select("td.tplCo > a").first();
            String companyName = companyLinkElement.text();
            String companyUrl = "https://jobkorea.co.kr" + companyLinkElement.attr("href");


            // strong 엘리먼트 내의 a 엘리먼트를 선택하여 채용 정보와 URL을 가져옵니다.
            Element jobLinkElement = jobElement.select("strong > a").first();
            String jobTitle = jobLinkElement.text();
            String jobUrl = "https://jobkorea.co.kr" + jobLinkElement.attr("href");

            // 신입/경력, 학력, 위치 등의 정보를 가져옵니다.
            Element etcElement = jobElement.select("p.etc").first();
            String jobInfo = etcElement.text();

            // 자세한 설명을 가져옵니다.
            Element descriptionElement = jobElement.select("p.dsc").first();
            String jobDescription = descriptionElement.text();


            //기업 정보를 출력하거나 다른 처리를 수행합니다.


            JobData job = new JobData(companyName, companyUrl, jobTitle, jobUrl, jobInfo, jobDescription);
            jobData.add(job);
        }
        for (int i = 0; i < jobData.size(); i++) {
            System.out.println(i + "번째 회사 ----");
            System.out.println("Company Name: " + jobData.get(i).getCompanyName());
            System.out.println("Company URL: " + jobData.get(i).getCompanyUrl());
            System.out.println("jobTitle : " + jobData.get(i).getTitle());
            System.out.println("Url : " + jobData.get(i).getUrl());
            System.out.println("Info : "  + jobData.get(i).getInfo());
            System.out.println("Description : " + jobData.get(i).getDescription());
            System.out.println("-------");
        }
        return jobData;
    }
}
