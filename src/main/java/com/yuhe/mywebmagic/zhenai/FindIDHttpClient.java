package com.yuhe.mywebmagic.zhenai;

import org.apache.http.client.HttpClient;
import org.apache.log4j.Logger;

import com.yuhe.mywebmagic.util.HttpsUtil;


public class FindIDHttpClient {
	private static Logger logger = Logger.getLogger(FindIDHttpClient.class);
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
		String message = null;
		for(int i=6805; i<10000; i++) {
			message = HttpsUtil.requestGet(BASE_REQUEST+i, client);
			if(message.indexOf("您的账号不存在")==-1) {	//没改字符串即表示存在该账号
				findZhenaiIDLogger.info(i);
			}else {
				logger.info("Not exist:"+i);	
			}
			Thread.sleep(1000);
		}

		client.getConnectionManager().shutdown();
	}


}
