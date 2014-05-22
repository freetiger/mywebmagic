package com.yuhe.mywebmagic.ablesky;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class CollegeStudentNameProcessor implements PageProcessor {
	private static final Logger logger = Logger.getLogger("studentName");

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    public void process(Page page) {
    	if(!page.getUrl().get().equals("http://www.ablesky.com/")) {
        	List<String> studentNameList = page.getJson().jsonPath("$.result.list[*].username").all();
        	page.putField("studentName", studentNameList);
        	logger.error("studentSize="+studentNameList.size()+" : "+studentNameList.toString()+"\n");
    	}
    	//
    	List<String> orgIdList = new ArrayList<String>();
		try {
	        File file=new File("C:/webmagiclog/CollegeOrgId.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
	        String temp=br.readLine();
	        while(temp!=null) {
	        	orgIdList.add("http://www.ablesky.com/college.do?action=getStudentGroupResult&start=0&limit=10000000&_=1400481311632&organizationId="+temp);
	            temp=br.readLine();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	page.addTargetRequests(orgIdList);
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
    	List<Pipeline> pipelines = new ArrayList<Pipeline>();
    	pipelines.add(new ConsolePipeline());
    	Spider.create(new CollegeStudentNameProcessor()).addUrl("http://www.ablesky.com/").setPipelines(pipelines).thread(10).run();
    }
}