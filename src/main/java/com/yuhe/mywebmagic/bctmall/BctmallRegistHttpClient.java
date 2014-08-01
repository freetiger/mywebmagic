package com.yuhe.mywebmagic.bctmall;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import com.yuhe.mywebmagic.util.HttpsUtil;
import com.yuhe.mywebmagic.util.StringUtil;

public class BctmallRegistHttpClient {
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
		String ___R_hidd = "";
		String R_hidd = HttpsUtil.requestGet("http://www.bctmall.cc/User/Reg.html?tid=20140522141201343008623", client);
		Matcher matcher = R_hidd_PATTERN.matcher(R_hidd);
		if(matcher.find()) {
			___R_hidd = matcher.group(1);
		}
		System.out.println(___R_hidd);
		//
		String webCode = HttpsUtil.getVerifyCode("http://www.bctmall.cc/VerifyCodeImage.ashx?q=0.4920468577183783", "jpg", client);
		System.out.println(webCode);
		//
    	String m_LoginPassword = StringUtil.getRandomLetterString(8).toLowerCase();
    	String m_LoginPassword_Confirm = m_LoginPassword;
    	String m_UserName = m_LoginPassword;
    	String mC_Email = m_UserName+"@163.com";
    	String provision = "true";
    	String toUrl = "";
    	String ruzType = "我是买家";	//%E6%88%91%E6%98%AF%E4%B9%B0%E5%AE%B6
    	String mC_RegCID = "2";
    	String mC_RegCIDC = "1";
    	
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
//    	String email_msg = HttpsUtil.requestGet("http://www.bctmall.cc/User/ExEmailApi?email="+registForm.getMC_Email()+"&r=1401186464091", client);
//    	String username_msg = HttpsUtil.requestGet("http://www.bctmall.cc/User/ExNameApi?n="+registForm.getM_UserName()+"&r=1401186464246", client);
    	String email_msg = null;
    	String username_msg = null;
		try {
			email_msg = HttpsUtil.requestPostData(client, "http://www.bctmall.cc/User/ExEmailApi?", "email="+registForm.getMC_Email()+"&r=1401186464091", "application/x-www-form-urlencoded", "UTF-8").getResponseString("UTF-8");
			username_msg = HttpsUtil.requestPostData(client, "http://www.bctmall.cc/User/ExNameApi?", "n="+registForm.getM_UserName()+"&r=1401186464246", "application/x-www-form-urlencoded", "UTF-8").getResponseString("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	System.out.println("email_msg\n"+email_msg);
    	System.out.println("username_msg\n"+username_msg);
    	//
		String url = "http://www.bctmall.cc/Reg.html?";
		String data = registForm.getRequestData();
		String strReString = null;
		try {
			strReString = HttpsUtil.requestPostData(client, url, data, "	text/html; charset=utf-8", "UTF-8").getResponseString("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		client.getConnectionManager().shutdown();
		System.out.println(data);
		System.out.println(strReString);
	}

}
