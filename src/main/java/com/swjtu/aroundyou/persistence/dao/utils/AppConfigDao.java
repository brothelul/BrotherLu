package com.swjtu.aroundyou.persistence.dao.utils;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.entity.utils.AppConfig;

public interface AppConfigDao extends GenericHibernateDao<AppConfig,AppConfig>{

	String NAME = "appConfigDao";
	
	public AppConfig getAppConfig(String appCat,String appName);
	
	public AppConfig getAppConfig(String appCat);
}
