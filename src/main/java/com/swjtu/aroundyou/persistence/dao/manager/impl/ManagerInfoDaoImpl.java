package com.swjtu.aroundyou.persistence.dao.manager.impl;

import org.springframework.stereotype.Repository;

import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;
import com.swjtu.aroundyou.persistence.dao.manager.ManagerInfoDao;
import com.swjtu.aroundyou.persistence.entity.manager.ManagerInfo;

@Repository(value=ManagerInfoDao.NAME)
public class ManagerInfoDaoImpl extends GenericHibernateDaoImpl<ManagerInfo, Integer>
		implements ManagerInfoDao {
}
