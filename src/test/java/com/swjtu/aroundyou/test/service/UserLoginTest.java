package com.swjtu.aroundyou.test.service;

import org.testng.annotations.Test;

import com.swjtu.aroundyou.biz.entity.user.UserLogin;
import com.swjtu.aroundyou.biz.service.user.UserLoginService;
import com.swjtu.aroundyou.test.AbstractTest;

public class UserLoginTest extends AbstractTest{

	@Test
	public void testGetUserLogin(){
		
		UserLoginService userLoginService = (UserLoginService)getBean("userLoginService");
		
		UserLogin userLogin = null;
		
		logger.info(userLogin.getUsername());
	}
}
