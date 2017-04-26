package com.swjtu.aroundyou.persistence.entity.utils;

import java.io.Serializable;
import java.util.Date;

public class AppConfig implements Serializable{

	private String appName;
	private String appCat;
	private String appValue;
	private String entryIP;
	private Integer entryId;
	private Date entryDate;
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppCat() {
		return appCat;
	}
	public void setAppCat(String appCat) {
		this.appCat = appCat;
	}
	public String getAppValue() {
		return appValue;
	}
	public void setAppValue(String appValue) {
		this.appValue = appValue;
	}
	public String getEntryIP() {
		return entryIP;
	}
	public void setEntryIP(String entryIP) {
		this.entryIP = entryIP;
	}
	public Integer getEntryId() {
		return entryId;
	}
	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}	
}
