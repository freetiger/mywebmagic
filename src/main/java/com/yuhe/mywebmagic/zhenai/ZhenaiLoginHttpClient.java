package com.yuhe.mywebmagic.zhenai;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import com.yuhe.mywebmagic.util.HttpsUtil;
import com.yuhe.mywebmagic.util.StringUtil;

public class ZhenaiLoginHttpClient {
	public static final Pattern R_hidd_PATTERN = Pattern.compile("var R_hidd = '([\\d]+)'");

	/** 
	 * 描述该方法的功能及算法流程
	 *
	 * @autor: heyuxing  2014-5-27 下午4:51:27
	 * @param args    
	 * @return void 
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClient client = HttpsUtil.createHttpClient();
		//
		String message = HttpsUtil.requestGet("http://profile.zhenai.com/login/loginactionindex.jsps?fid=&fromurl=&loginInfo=15821389004&loginmode=2&mid=&password=1161hyx&redirectUrl=&whereLogin=index&whichTV=", client);
		System.out.println(message);
		message = HttpsUtil.requestGet("http://album.zhenai.com/personal/newInfoCountAjax.jsps?r=0.6271263033825859&callback=jQuery18303444675935448065_1401265541588&_=1401266082498", client);
		System.out.println(message);
		message = HttpsUtil.requestGet("http://album.zhenai.com/profile/getmemberdata.jsps?memberid=65288586", client);
		System.out.println(message);
		 //乱码，要考虑一下
		message = HttpsUtil.requestGet("http://album.zhenai.com/personal/sendmail.jsps?source=1&mailcontent=hello，路过打个招呼&mbMailFlag=0&nickname=&objmemberid=65288586&paySendMail=0&token=&type=0&workerId=", client);
		System.out.println(message);
	}

}
