package com.swjtu.aroundyou.test.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.testng.annotations.Test;

import com.swjtu.aroundyou.utils.HttpRequestUrl;

public class TestUtils {

	
	@Test
	public void testRequestUrl() throws UnsupportedEncodingException{
		
		String name = new String(URLEncoder.encode("成都", "utf-8"));
		
		String url=" http://api.avatardata.cn/Weather/Query?key=877718fcd9694ab0bb4dfe89dc5bfc45&cityname="+name;
		String json = null;
		try {
			json = HttpRequestUrl.getUrlResponse(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(json);
	}
}
