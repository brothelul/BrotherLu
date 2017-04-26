package com.swjtu.aroundyou.persistence.dao.manager;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.entity.manager.ManagerLogin;

public interface ManagerLoginDao extends GenericHibernateDao<ManagerLogin, Integer>{

	String NAME = "managerLoginDao";
	
	ManagerLogin getManagerLogin(String managerName,String password);
}
