package com.swjtu.aroundyou.biz.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.aroundyou.biz.service.user.UserService;
import com.swjtu.aroundyou.persistence.dao.user.UserInfoDao;
import com.swjtu.aroundyou.persistence.dao.user.UserLoginDao;
import com.swjtu.aroundyou.persistence.entity.user.UserId;
import com.swjtu.aroundyou.persistence.entity.user.UserInfo;

@Service(value=UserService.NAME)
public class UserServiceImpl implements UserService{

	@Autowired
	private UserLoginDao userLoginDao;
	@Autowired
	private UserInfoDao userInfoDao;
	
	public UserInfo getUserLogin(String username,String password){
				
		UserId id = userLoginDao.findUserLoginForLogin(username, password);
		UserInfo userInfo = userInfoDao.getById(id);
		
		return userInfo;
	}

	@Override
	public void saveUserLoginInfo(UserInfo userInfo) {
		
		userInfoDao.update(userInfo);
	}
}
