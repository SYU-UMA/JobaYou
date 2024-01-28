package com.jobayour.modules.crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/crawling")
public class CrawlingController {

    private CrawlingService crawlingService;

    public CrawlingController(CrawlingService crawlingService) {
        this.crawlingService = crawlingService;
    }

    @GetMapping("/data")
    public ArrayList<JobData> crawlingData() throws IOException {
        ArrayList<JobData> jobData = crawlingService.getJobData();
        return jobData;
    }

}
