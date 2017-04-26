package com.swjtu.aroundyou.persistence.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;
import com.swjtu.aroundyou.persistence.dao.user.UserInfoDao;
import com.swjtu.aroundyou.persistence.entity.user.UserInfo;

@Repository(value=UserInfoDao.NAME)
public class UserInfoDaoImpl extends GenericHibernateDaoImpl<UserInfo,Integer> implements UserInfoDao {

	@Override
	public Pagination<UserInfo> getUsers(Integer page, Integer pageSize) {
		String hql = "from UserInfo";
		return findPagination(hql, page, pageSize, null);
	}

}
