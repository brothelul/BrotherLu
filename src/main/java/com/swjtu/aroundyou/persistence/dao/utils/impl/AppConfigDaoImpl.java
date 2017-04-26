package com.swjtu.aroundyou.persistence.dao.utils.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;
import com.swjtu.aroundyou.persistence.dao.utils.AppConfigDao;
import com.swjtu.aroundyou.persistence.entity.utils.AppConfig;

@Repository(value=AppConfigDao.NAME)
public class AppConfigDaoImpl extends GenericHibernateDaoImpl<AppConfig,AppConfig> implements AppConfigDao{

	@Override
	public AppConfig getAppConfig(String appCat, String appName) {
		String hql = "from AppConfig where appCat = :appCat and appName = :appName";
		Query query = getSession().createQuery(hql)
				                  .setParameter("appCat", appCat)
				                  .setParameter("appName", appName);
		
		if (query.list() != null && query.list().size() > 0) {
			return (AppConfig) query.list().get(0);
		}
		return null;
	}

	@Override
	public AppConfig getAppConfig(String appCat) {
		String hql = "from AppConfig where appCat = :appCat";
		Query query = getSession().createQuery(hql)
				                  .setParameter("appCat", appCat);
		if (query.list() != null && query.list().size() > 0) {
			return (AppConfig) query.list().get(0);
		}
		return null;
	}

}
