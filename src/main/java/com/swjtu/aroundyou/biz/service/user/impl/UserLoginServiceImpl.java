package com.swjtu.aroundyou.biz.service.user.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.aroundyou.biz.entity.user.UserLogin;
import com.swjtu.aroundyou.biz.service.user.UserLoginService;
import com.swjtu.aroundyou.persistence.dao.user.UserLoginDao;
import com.swjtu.aroundyou.persistence.dto.user.UserIdDTO;
import com.swjtu.aroundyou.persistence.dto.user.UserLoginDTO;

@Service(value=UserLoginService.NAME)
public class UserLoginServiceImpl implements UserLoginService{

	@Autowired
	private UserLoginDao userLoginDao;
	
	public UserLogin getUserLogin(String username,String password){
		
		UserIdDTO id = new UserIdDTO(1000, 0);
		UserLogin userLogin = new UserLogin();
		
		UserLoginDTO userLoginDTO = userLoginDao.getById(id);
		
		BeanUtils.copyProperties(userLoginDTO, userLogin);
		
		userLogin.setUserNo(id.getUserNo());
		userLogin.setUserType(id.getUserType());
		
		return userLogin;
	}
}
