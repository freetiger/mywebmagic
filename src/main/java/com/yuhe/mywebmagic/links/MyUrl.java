package com.yuhe.mywebmagic.links;

import java.util.HashSet;
import java.util.Set;

import com.yuhe.mywebmagic.util.StringUtil;

public class MyUrl {
	private String url;
	
	public MyUrl() {
		
	}
	
	public MyUrl(String url) {
		if(StringUtil.isEmptyString(url)) {
			this.url = "";
		}else {
			url = url.trim();
			if(url.endsWith("/")) {
				this.url = url.substring(0, url.length()-1);
			}else {
				this.url = url;
			}
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
    @Override
    public int hashCode() {
    	return 0;
    }
    
	@Override
	public boolean equals(Object obj) {
        if (obj == null) {  
            return false;  
        }  
  
        if (this == obj) {  
            return true;  
        }  
  
        if (!(obj instanceof MyUrl)) {  
            return false;  
        }  
  
        MyUrl myUrl = (MyUrl) obj;
        String currentStart = this.getUrl().substring(0, this.getUrl().lastIndexOf("/"));
        String myUrlStart = myUrl.getUrl().substring(0, myUrl.getUrl().lastIndexOf("/"));
        if(currentStart.equals(myUrlStart)) {
        	return true;
        }

		return false;
	}
	
	public static void main(String[] args) {
		MyUrl myUrl_1 = new MyUrl("http://songshuhui.net/archives/89827");
		MyUrl myUrl_2 = new MyUrl("http://songshuhui.net/archives/90101");
		MyUrl myUrl_3 = new MyUrl("http://songshuhui.net/archives/date/2012/08");
		Set<MyUrl> allLinksSet = new HashSet<MyUrl>();
		allLinksSet.add(myUrl_1);
		allLinksSet.add(myUrl_2);
		allLinksSet.add(myUrl_3);
		//
		System.out.println(allLinksSet.size());
		for(MyUrl tempUrl : allLinksSet) {
			System.out.println(tempUrl.getUrl());
		}
	}
}
