package com.swjtu.aroundyou.web.controller.weather;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.swjtu.aroundyou.biz.service.utils.AppConfigService;
import com.swjtu.aroundyou.persistence.entity.utils.WeatherUrl;
import com.swjtu.aroundyou.persistence.entity.weather.Weather;
import com.swjtu.aroundyou.utils.HttpRequestUrl;

@Controller
@RequestMapping(value="user/")
public class WeatherController {

	private static Logger logger = Logger.getLogger(WeatherController.class);
	private static final String CAT = "WEATHER";
	private static final String KEY_NAME = "KEY";
	private static final String URI_NAME = "URI";
	
	@Autowired
	private AppConfigService appConfigService;
	
	@RequestMapping("weather.do")
	@ResponseBody
	public String getWeather(HttpServletRequest request){
		
		Weather weather = new Weather();
		
		try {
			String name = "成都";
			
			String urlEncoderCityName = new String(URLEncoder.encode(name, "utf-8"));		
			String url = getWeatherUrl(urlEncoderCityName).toString();
			
			String weatherJson = HttpRequestUrl.getUrlResponse(url);
			logger.info("weather info from avatardata:"+weatherJson);			
			JSONObject result = JSONObject.parseObject(weatherJson).getJSONObject("result");
			
			if (result != null) {
				
				JSONObject realtime = result.getJSONObject("realtime");
				
				if (realtime != null) {					
					weather.setCityName(realtime.getString("city_name"));
					weather.setDate(realtime.getDate("date"));
					JSONObject wind = realtime.getJSONObject("wind");
					weather.setWind(wind.getString("direct")+wind.getString("power"));					
					weather.setWeather(realtime.getJSONObject("weather").getString("info"));
				}
				
				JSONObject pm25 = result.getJSONObject("pm25");				
				weather.setQuality(pm25.getJSONObject("pm25").getString("quality")+pm25.getJSONObject("pm25").getString("pm10"));				
				JSONArray weathers = result.getJSONArray("weather");
				
				if (weathers != null && weathers.size() > 0) {				
					JSONObject todayWeather = (JSONObject) weathers.get(0);					
					JSONObject info = todayWeather.getJSONObject("info");				
					weather.setLowTemp(info.getJSONArray("night").getString(2));
					weather.setHighTemp(info.getJSONArray("day").getString(2));
				}
			}									
		} catch (UnsupportedEncodingException e) {
			
			logger.error("cityname has some probelm",e);
		} catch (IOException e) {
			
			logger.error("connect failed",e);
		} catch (NullPointerException e) {
			
			logger.error("parse json failed",e);
		}
		
		return JSON.toJSONString(weather);
	}
	
	private WeatherUrl getWeatherUrl(String cityname){
		WeatherUrl weatherUrl = new WeatherUrl();
		
		String key = appConfigService.getAppConfigValue(CAT, KEY_NAME);
		String uri = appConfigService.getAppConfigValue(CAT, URI_NAME);
		weatherUrl.setKey(key);
		weatherUrl.setUri(uri);
		weatherUrl.setCityName(cityname);
		
		return weatherUrl;
	}
}
