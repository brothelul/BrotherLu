package com.swjtu.aroundyou.persistence.dao.manager;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.entity.manager.ManagerLoginLog;

public interface ManagerLoginLogDao extends GenericHibernateDao<ManagerLoginLog, Integer>{

	String NAME = "managerLoginLogDao";
	ManagerLoginLog getManagerLoginLog(Integer managerNo);
}
