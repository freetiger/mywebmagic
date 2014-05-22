package com.yuhe.mywebmagic.ablesky;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class CollegeOrgNameProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public void process(Page page) {
    	List<String> collegeAccountNameList = page.getJson().jsonPath("$.result.list[*].collegeAccountName").all();
    	page.putField("collegeAccountName", collegeAccountNameList);
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
    	List<Pipeline> pipelines = new ArrayList<Pipeline>();
    	FilePipeline filePipeline = new FilePipeline();
    	filePipeline.setPath("C:/webmagiclog");
    	pipelines.add(filePipeline);
    	pipelines.add(new ConsolePipeline());
        Spider.create(new CollegeOrgNameProcessor()).addUrl("http://edu.ablesky.com/college.do?action=getCollegeList&collegeType=1&collegeName=&_=1400221074403").setPipelines(pipelines).thread(5).run();
        Spider.create(new CollegeOrgNameProcessor()).addUrl("http://edu.ablesky.com/college.do?action=getCollegeList&collegeType=2&collegeName=&_=1400221074403").setPipelines(pipelines).thread(5).run();
        Spider.create(new CollegeOrgNameProcessor()).addUrl("http://edu.ablesky.com/college.do?action=getCollegeList&collegeType=3&collegeName=&_=1400221074403").setPipelines(pipelines).thread(5).run();
    }
}