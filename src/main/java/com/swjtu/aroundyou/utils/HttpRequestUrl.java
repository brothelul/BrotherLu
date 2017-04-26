package com.swjtu.aroundyou.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * @author brotherlu
 * @date 下午9:20:13
 * <p>描述：请求指定url获取返回值</p>
 */
public class HttpRequestUrl {

	public static String getUrlResponse(String url) throws IOException {
		
		StringBuffer response = new StringBuffer();
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();

		urlConnection.setDoInput(true);
		urlConnection.setRequestMethod("GET");
		urlConnection.connect();
		
		BufferedReader in =
				new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"utf-8"));
        
		String inputLine = null;
        while ((inputLine = in.readLine()) != null) {
			
			response.append(inputLine);
		}
        in.close();
        
        return response.toString();
	}
}
