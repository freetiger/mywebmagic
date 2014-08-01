package com.yuhe.mywebmagic.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.yuhe.mywebmagic.bctmall.BctmallRegistProcessor;
import com.yuhe.mywebmagic.bctmall.RegistForm;
import com.yuhe.mywebmagic.example.ConsolePipeline;
import com.yuhe.mywebmagic.util.HttpsUtil;
import com.yuhe.mywebmagic.util.StringUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class SongshuhuiListProcessor implements PageProcessor {
	private static Logger songshuhuiLogger = Logger.getLogger("songshuhui");

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public void process(Page page) {
    	List<String> title = page.getHtml().regex("http://songshuhui.net/archives/\\d+[^>]+>([^<]+)").all();
//    	page.putField("title", title);
    	List<String> orgNameList = new ArrayList<String>();
    	for(int i=1; i<308; i++) {
    		orgNameList.add("http://songshuhui.net/archives/tag/%E5%8E%9F%E5%88%9B/page/"+i);
    	}
    	page.addTargetRequests(orgNameList);
    	songshuhuiLogger.info(title);
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
    	List<Pipeline> pipelines = new ArrayList<Pipeline>();
//    	FilePipeline filePipeline = new FilePipeline();
//    	filePipeline.setPath("C:/webmagiclog");
//    	pipelines.add(filePipeline);
//    	pipelines.add(new ConsolePipeline());
        Spider.create(new SongshuhuiListProcessor()).addUrl("http://songshuhui.net/archives/tag/%E5%8E%9F%E5%88%9B").setPipelines(pipelines).thread(1).run();
    }

}
