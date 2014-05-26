package com.yuhe.mywebmagic.bctmall;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class BctmallRegistProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public void process(Page page) {
    	//var R_hidd = '9189';
    	String R_hidd = page.getHtml().regex("var R_hidd = '([\\d]+)'").get();
    	page.putField("R_hidd", R_hidd);
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
    	List<Pipeline> pipelines = new ArrayList<Pipeline>();
//    	FilePipeline filePipeline = new FilePipeline();
//    	filePipeline.setPath("C:/webmagiclog");
//    	pipelines.add(filePipeline);
    	pipelines.add(new ConsolePipeline());
        Spider.create(new BctmallRegistProcessor()).addUrl("http://www.bctmall.cc/User/Reg.html?tid=20140522141201343008623").setPipelines(pipelines).thread(5).run();
    }
}
