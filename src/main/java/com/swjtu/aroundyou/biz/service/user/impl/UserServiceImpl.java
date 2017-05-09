package com.swjtu.aroundyou.biz.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.aroundyou.biz.service.user.UserService;
import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.dao.user.UserInfoDao;
import com.swjtu.aroundyou.persistence.dao.user.UserLoginDao;
import com.swjtu.aroundyou.persistence.dao.user.UserLoginLogDao;
import com.swjtu.aroundyou.persistence.entity.user.UserInfo;
import com.swjtu.aroundyou.persistence.entity.user.UserLogin;
import com.swjtu.aroundyou.persistence.entity.user.UserLoginLog;

@Service(value=UserService.NAME)
public class UserServiceImpl implements UserService{

	@Autowired
	private UserLoginDao userLoginDao;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private UserLoginLogDao userLoginLogDao;
	
	public UserInfo getUserLogin(String username,String password){
				
		Integer userNo = userLoginDao.findUserLoginForLogin(username, password);
		UserInfo userInfo = userInfoDao.getById(userNo);
		
		return userInfo;
	}

	@Override
	public void updateUserLoginInfo(UserInfo userInfo) {
		
		userInfoDao.update(userInfo);
	}

	@Override
	public void saveUserLoginLog(UserLoginLog userLoginLog) {
		userLoginLogDao.save(userLoginLog);
	}

	@Override
	public UserInfo getUserInfo(Integer userNo) {		
		return userInfoDao.getById(userNo);
	}

	@Override
	public Integer saveUserLogin(UserLogin userLogin) {		
		return userLoginDao.save(userLogin);
	}

	@Override
	public void saveUserInfo(UserInfo userInfo) {
		userInfoDao.saveOrUpdate(userInfo);
	}

	@Override
	public Pagination<UserInfo> getAllUsers(Integer page, Integer pageSize) {
		return userInfoDao.getUsers(page, pageSize);
	}

	@Override
	public Pagination<UserInfo> getIdUsers(Integer page, Integer pageSize) {
		return userInfoDao.getIdUsers(page,pageSize);
	}

	@Override
	public UserLogin getUserLogin(Integer userNo) {
		return userLoginDao.getById(userNo);
	}

	@Override
	public void updateUserLogin(UserLogin userLogin) {
		userLoginDao.update(userLogin);
	}

	@Override
	public boolean validateUsername(String username) {
		UserLogin userLogin = userLoginDao.validateUsername(username);
		if (userLogin == null) {
			return true;
		}
		return false;
	}
}
