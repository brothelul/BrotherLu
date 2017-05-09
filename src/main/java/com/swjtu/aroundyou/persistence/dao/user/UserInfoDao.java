package com.swjtu.aroundyou.persistence.dao.user;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.entity.user.UserInfo;

public interface UserInfoDao extends GenericHibernateDao<UserInfo,Integer> {

	String NAME = "userInfoDao";
	
	Pagination<UserInfo> getUsers(Integer page,Integer pageSize);
	
	Pagination<UserInfo> getIdUsers(Integer page, Integer pageSize);
}
