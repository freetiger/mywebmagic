package com.yuhe.mywebmagic.zhenai;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.client.HttpClient;

import com.yuhe.mywebmagic.util.HttpsUtil;

public class Test {

	/** 
	 * 描述该方法的功能及算法流程
	 *
	 * @autor: heyuxing  2014-6-3 下午5:17:56
	 * @param args    
	 * @return void 
	 * @throws UnsupportedEncodingException 
	 */

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		HttpClient client = HttpsUtil.createHttpClient();
//		//
//		String message = HttpsUtil.requestGet("http://profile.zhenai.com/login/loginactionindex.jsps?fid=&fromurl=&loginInfo=15821389004&loginmode=2&mid=&password=1161hyx&redirectUrl=&whereLogin=index&whichTV=", client);
//		//System.out.println(message);
//		//
//		message = HttpsUtil.requestGet("http://im.zhenai.com/im/FootMsg?workCity=10103004&_=1401786946173&callback="+URLEncoder.encode("jQuery183034692054101671455_1401786644035 and 1=1","GBK"), client);
//		System.out.println(message);
		
		String message = null;
		for(int i=1; i<50;i++) {
			message = HttpsUtil.requestGet("http://songshuhui.net/archives/category/major/biology/page/"+i+"?tag=%E5%8E%9F%E5%88%9B&pagetag=yuanchuang", client);
		}		
		System.out.println(message);
	}

}
