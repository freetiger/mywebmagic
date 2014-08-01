package com.yuhe.mywebmagic.test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import com.yuhe.mywebmagic.util.FileUtil;
import com.yuhe.mywebmagic.util.HttpsUtil;
import com.yuhe.mywebmagic.util.ImageGrayBinaryUtil;
import com.yuhe.mywebmagic.util.StringUtil;

public class ZhihuRegistHttpClient {
	//<input type="hidden" name="_xsrf" value="0450a0a019414444a394f876e0e00ceb"/>
	public static final Pattern R_hidd_PATTERN = Pattern.compile("<input.*name=\"_xsrf\".*value=\"([A-Za-z0-9]+)\"");
	
	public static void regist() {
		HttpClient client = HttpsUtil.createHttpClient();
		//
		String _xsrf = "";
		String _xsrfResponse = HttpsUtil.requestGet("http://www.zhihu.com/#signup", client);
		Matcher matcher = R_hidd_PATTERN.matcher(_xsrfResponse);
		if(matcher.find()) {
			_xsrf = matcher.group(1);
		}
		System.out.println(_xsrf);
		//1403588532497
		String captcha = null;
		while(true) {
			captcha = HttpsUtil.getVerifyCode("http://www.zhihu.com/captcha.gif?r="+new Date().getTime(), "jpg", client);
			System.out.println(captcha);
			if(captcha.length()==4) {
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//
		String email = "hyx1166@163.com";
		String first_name = "1166";
		String last_name = "he";
		String password = "he12345678";
		String registerResponse = null;
		try {
			registerResponse = HttpsUtil.requestPostData(client, "http://www.zhihu.com/register/account?", "_xsrf="+_xsrf+"&email="+email+"&first_name="+first_name+"&last_name="+last_name+"&password="+password+"&captcha="+captcha, "application/x-www-form-urlencoded", "UTF-8").getResponseString("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(registerResponse);
		client.getConnectionManager().shutdown();
	}
	
	public static void getZhihuCodes() {
		HttpClient client = HttpsUtil.createHttpClient();
		String captcha = null;
		for(int i=0; i<100; i++) {
			File file = FileUtil.inputstreamToFile(HttpsUtil.requestGetInputStream("http://www.zhihu.com/captcha.gif?r="+new Date().getTime(), client), new File("D:/verifycode/zhihu/"+StringUtil.getRandomLetterString(32)+".jpg"));
			String outputFile = "D:/verifycode/zhihu/gray_binary/"+StringUtil.getRandomLetterString(32)+".jpg";
			ImageGrayBinaryUtil.grayBinary(file, new File(outputFile));
			System.out.println(i+" : "+outputFile);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		client.getConnectionManager().shutdown();
	}

	/** 
	 * 描述该方法的功能及算法流程
	 *
	 * @autor: heyuxing  2014-5-27 下午4:51:27
	 * @param args    
	 * @return void 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws ParseException 
	 */

	public static void main(String[] args) {
		getZhihuCodes();
	}

}
