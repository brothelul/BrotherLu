package com.swjtu.aroundyou.test.service;

import org.testng.annotations.Test;

import com.swjtu.aroundyou.biz.entity.user.UserLogin;
import com.swjtu.aroundyou.biz.service.user.UserService;
import com.swjtu.aroundyou.test.AbstractTest;

public class UserLoginTest extends AbstractTest{

	@Test
	public void testGetUserLogin(){
		
		UserService userLoginService = (UserService)getBean("userLoginService");
		
		UserLogin userLogin = null;
		
		logger.info(userLogin.getUsername());
	}
}
