package com.swjtu.aroundyou.biz.service.utils.impl;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.aroundyou.biz.service.utils.AppConfigService;
import com.swjtu.aroundyou.persistence.dao.utils.AppConfigDao;
import com.swjtu.aroundyou.persistence.entity.utils.AppConfig;

@Service(value = AppConfigService.NAME)
public class AppConfigServiceImpl implements AppConfigService {

	@Autowired
	private AppConfigDao appConfigDao;

	@Override
	public String getAppConfigValue(String cat, String name) {
		AppConfig appConfig = appConfigDao.getAppConfig(cat, name);
		
		if (appConfig != null) {
			return appConfig.getAppValue();
		}
		return null;
	}

	@Override
	public AppConfig getAppConfig(String appCat) {
		AppConfig appConfig = appConfigDao.getAppConfig(appCat);
		if (appConfig == null) {
			appConfig = new AppConfig();
		}
		return appConfig;
	}
}
