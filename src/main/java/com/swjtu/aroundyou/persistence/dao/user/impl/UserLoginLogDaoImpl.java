package com.swjtu.aroundyou.persistence.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;
import com.swjtu.aroundyou.persistence.dao.user.UserLoginLogDao;
import com.swjtu.aroundyou.persistence.entity.user.UserLoginLog;

@Repository(value = UserLoginLogDao.NAME)
public class UserLoginLogDaoImpl extends GenericHibernateDaoImpl<UserLoginLog,Integer> implements
		UserLoginLogDao {

}
