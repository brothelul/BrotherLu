package com.swjtu.aroundyou.biz.service.user;

import com.swjtu.aroundyou.biz.entity.user.UserInfo;
import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;

public interface UserInfoService extends GenericHibernateDao<UserInfo>{

	String NAME = "userInfoService";
}
