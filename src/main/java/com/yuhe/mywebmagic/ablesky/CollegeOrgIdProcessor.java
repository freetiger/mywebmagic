package com.yuhe.mywebmagic.ablesky;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import com.yuhe.mywebmagic.util.FileUtil;

public class CollegeOrgIdProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public void process(Page page) {
    	String collegeAccountId = page.getHtml().links().regex("collegeRedirect.do\\?action=toStudentTimeline&organizationId=(\\d+)").get();
    	page.putField("collegeOrgId", collegeAccountId);
    	//
    	String[] orgNameFiles = new String[]{
    			"C:/webmagiclog/edu.ablesky.com/11c488ac86bf9d01ee6aed5c72c03b79.html",
    			"C:/webmagiclog/edu.ablesky.com/1176d25bdfdf34c0d8fcc1b1bf494544.html",
    			"C:/webmagiclog/edu.ablesky.com/b801a21a692a9546acfbc697be65b085.html"
    	};
    	for(String orgNameFile : orgNameFiles) {
        	String[] orgNames = FileUtil.loadFile(orgNameFile).split("\n");
        	List<String> orgNameList = new ArrayList<String>();
        	for(String orgName : orgNames) {
        		if(!orgName.contains(":")) {
                	orgNameList.add(orgName);
        		}
        	}
        	page.addTargetRequests(orgNameList);	//相对路径
    	}
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
    	List<Pipeline> pipelines = new ArrayList<Pipeline>();
    	pipelines.add(new ConsolePipeline());
    	Spider.create(new CollegeOrgIdProcessor()).addUrl("http://www.ablesky.com/").setPipelines(pipelines).thread(10).run();
    }
}