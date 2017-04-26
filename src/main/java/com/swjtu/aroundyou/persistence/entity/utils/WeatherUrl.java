package com.swjtu.aroundyou.persistence.entity.utils;

public class WeatherUrl {

	private String uri;
	private String key;
	private String cityName;
		
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	@Override
	public String toString() {
		return uri+"?key="+key+"&cityname="+cityName;
	}
}
