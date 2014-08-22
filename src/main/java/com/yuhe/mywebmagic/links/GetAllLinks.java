package com.yuhe.mywebmagic.links;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.HttpClient;

import com.yuhe.mywebmagic.util.HttpsUtil;
import com.yuhe.mywebmagic.util.StringUtil;

public class GetAllLinks {
	private HttpClient client = HttpsUtil.createHttpClient();
	/**多次使用的话不需要重新编译正则表达式了，对于频繁调用能提高效率*/  
	public static final Pattern URL_PATTERN = Pattern.compile("<a.*?href=\"([^\"]+)\"[^>]*>(.*?)</a>");
	
	private Set<MyUrl> allLinksSet = new HashSet<MyUrl>();
	private Queue<MyUrl> pendingLinksQueue = new LinkedList<MyUrl>();
	private final String HOST = "vaikan.com";

	public void handleLink(String url) {
		try{
			Set<MyUrl> matchLinksSet = new HashSet<MyUrl>();
			String page = HttpsUtil.requestGet(url, client);
			if(!StringUtil.isEmptyString(page)) {
				//匹配url页面的所有url
				Matcher matcher = URL_PATTERN.matcher(page);
				String matchUrl = null;
				while(matcher.find()) {
					matchUrl = matcher.group(1);
					if(!StringUtil.isEmptyString(matchUrl) && matchUrl.indexOf(HOST)!=-1) {
						matchLinksSet.add(new MyUrl(matchUrl));	
					}
				}	
				//将匹配的url加入处理队列
				matchLinksSet.removeAll(allLinksSet);
				allLinksSet.addAll(matchLinksSet);
				pendingLinksQueue.addAll(matchLinksSet);
				pendingLinksQueue.poll();
			}
		}catch(Exception e) {
			System.out.println();
			;
		}
	}
	
	/** 
	 * 描述该方法的功能及算法流程
	 *
	 * @autor: heyuxing  2014-8-22 下午3:15:37
	 * @param args    
	 * @return void 
	 */

	public static void main(String[] args) {
		MyUrl myUrl = new MyUrl("http://www.vaikan.com");
		GetAllLinks getAllLinks = new GetAllLinks();
		getAllLinks.pendingLinksQueue.add(myUrl);
		getAllLinks.allLinksSet.add(myUrl);
		MyUrl url = null;
		do{
			url = getAllLinks.pendingLinksQueue.poll();
			if(url==null || getAllLinks.allLinksSet.size()>100) {
				break;
			}
			getAllLinks.handleLink(url.getUrl());
		}while(true);
		
		System.out.println(getAllLinks.allLinksSet.size());
		for(MyUrl tempUrl : getAllLinks.allLinksSet) {
			System.out.println(tempUrl.getUrl());
		}
	}

}
