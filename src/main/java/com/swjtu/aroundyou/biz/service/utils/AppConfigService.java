package com.swjtu.aroundyou.biz.service.utils;

import com.swjtu.aroundyou.persistence.entity.utils.AppConfig;

public interface AppConfigService {

	String NAME = "appConfigService";
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年4月4日下午4:55:10
	 * @param String
	 * <p>描述：根据分类查找</p>
	 */
	public String getAppConfigValue(String cat,String name);
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年4月4日下午4:56:01
	 * @param AppConfig
	 * <p>描述：根据appCat查找Appconfig</p>
	 */
	public AppConfig getAppConfig(String appCat);
}
