package com.swjtu.aroundyou.biz.service.user.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.aroundyou.biz.entity.user.UserInfo;
import com.swjtu.aroundyou.biz.entity.user.UserLogin;
import com.swjtu.aroundyou.biz.service.user.UserLoginService;
import com.swjtu.aroundyou.persistence.dao.user.UserInfoDao;
import com.swjtu.aroundyou.persistence.dao.user.UserLoginDao;
import com.swjtu.aroundyou.persistence.dto.user.UserIdDTO;
import com.swjtu.aroundyou.persistence.dto.user.UserInfoDTO;
import com.swjtu.aroundyou.persistence.dto.user.UserLoginDTO;
import com.swjtu.aroundyou.utils.BeanCopyUtil;

@Service(value=UserLoginService.NAME)
public class UserLoginServiceImpl implements UserLoginService{

	@Autowired
	private UserLoginDao userLoginDao;
	@Autowired
	private UserInfoDao userInfoDao;
	
	public UserInfo getUserLogin(String username,String password){
		
		UserInfo userInfo = new UserInfo();
		
		UserIdDTO id = userLoginDao.findUserLoginForLogin(username, password);
		UserInfoDTO userInfoDTO = userInfoDao.getById(id);
		
		BeanCopyUtil.copy(userInfoDTO, userInfo);
	
		return userInfo;
	}
}
