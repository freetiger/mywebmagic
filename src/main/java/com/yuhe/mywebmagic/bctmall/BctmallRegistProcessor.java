package com.yuhe.mywebmagic.bctmall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;

import com.yuhe.mywebmagic.util.HttpsUtil;
import com.yuhe.mywebmagic.util.StringUtil;

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
    	String ___R_hidd = page.getHtml().regex("var R_hidd = '([\\d]+)'").get();
    	String webCode = HttpsUtil.getVerifyCode("http://www.bctmall.cc/VerifyCodeImage.ashx?q=0.4920468577183783");
    	String m_LoginPassword = StringUtil.getRandomLetterString(8);
    	String m_LoginPassword_Confirm = m_LoginPassword;
    	String m_UserName = m_LoginPassword;
    	String mC_Email = m_UserName+"@163.com";
    	String provision = "true";
    	String toUrl = "";
    	String ruzType = "我是买家";	//%E6%88%91%E6%98%AF%E4%B9%B0%E5%AE%B6
    	String mC_RegCID = "1";
    	String mC_RegCIDC = "2";
    	
    	RegistForm registForm = new RegistForm();
    	registForm.set___R_hidd(___R_hidd);
    	registForm.setM_LoginPassword(m_LoginPassword);
    	registForm.setM_LoginPassword_Confirm(m_LoginPassword_Confirm);
    	registForm.setM_UserName(m_UserName);
    	registForm.setMC_Email(mC_Email);
    	registForm.setMC_RegCID(mC_RegCID);	//省份
    	registForm.setMC_RegCIDC(mC_RegCIDC);//城市
    	registForm.setProvision(provision);
    	registForm.setRuzType(ruzType);
    	registForm.setToUrl(toUrl);
    	registForm.setWebCode(webCode);
    	System.out.println(StringUtil.printParam(registForm));
    	//
		String url = "http://www.bctmall.cc/Reg.html?";
		String data = registForm.getRequestData();
		String strReString = null;
		try {
			strReString = HttpsUtil.requestPostData(url, data, "application/x-www-form-urlencoded", "UTF-8").getResponseString("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(data);
		System.out.println(strReString);
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
        Spider.create(new BctmallRegistProcessor()).addUrl("http://www.bctmall.cc/User/Reg.html?tid=20140522141201343008623").setPipelines(pipelines).thread(5).run();
    }
}
