package com.yuhe.mywebmagic.zhenai;

import org.apache.http.client.HttpClient;
import org.apache.log4j.Logger;

import com.yuhe.mywebmagic.util.HttpsUtil;


public class SqlHitHttpClient {
	private static Logger logger = Logger.getLogger(SqlHitHttpClient.class);
	private static Logger findZhenaiIDLogger = Logger.getLogger("findZhenaiID");
	
	public static final String BASE_REQUEST = "http://profile.zhenai.com/login/loginactionindex.jsps?fid=&formHuntWedding=&fromurl=&isTpRedirect=&loginmode=2&mid=&redirectUrl=&rememberpassword=1&whereLogin=login_page&whichTV=&password=100&loginInfo=";

	/** 
	 * 描述该方法的功能及算法流程
	 *
	 * @autor: heyuxing  2014-5-27 下午4:51:27
	 * @param args    
	 * @return void 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		HttpClient client = HttpsUtil.createHttpClient();
		//
		String message = HttpsUtil.requestGet(BASE_REQUEST, client);
		logger.info(message);
		client.getConnectionManager().shutdown();
	}


}
