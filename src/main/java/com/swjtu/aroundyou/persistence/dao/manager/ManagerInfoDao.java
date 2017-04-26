package com.swjtu.aroundyou.persistence.dao.manager;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.entity.manager.ManagerInfo;

public interface ManagerInfoDao extends GenericHibernateDao<ManagerInfo, Integer>{

	String NAME = "managerInfoDao";
}
