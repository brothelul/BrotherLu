package com.swjtu.aroundyou.persistence.dao.user;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.entity.user.UserLoginLog;

public interface UserLoginLogDao extends GenericHibernateDao<UserLoginLog,Integer> {

	String NAME = "userLoginDao";
}
