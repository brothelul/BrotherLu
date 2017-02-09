package com.swjtu.aroundyou.web.controller.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swjtu.aroundyou.biz.entity.user.UserLogin;
import com.swjtu.aroundyou.biz.service.user.UserInfoService;
import com.swjtu.aroundyou.biz.service.user.UserLoginService;

@Controller(value="userContoller")
public class UserContoller {

	private Logger logger = Logger.getLogger(UserContoller.class);
	
	@Autowired
	private UserLoginService userLoginService;
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(name="/login",method=RequestMethod.GET)
	public String getUserLogin(){
		
		UserLogin userLogin = userLoginService.getUserLogin("", "");
		logger.info(userLogin.getUsername());
		
		return "redirect:index.jsp";
	}
}
