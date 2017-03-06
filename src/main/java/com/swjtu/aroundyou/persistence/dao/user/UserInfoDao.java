package com.swjtu.aroundyou.persistence.dao.user;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.entity.user.UserInfo;

public interface UserInfoDao extends GenericHibernateDao<UserInfo> {

	String NAME = "userInfoDao";
}
