package com.swjtu.aroundyou.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swjtu.aroundyou.biz.entity.user.UserInfo;
import com.swjtu.aroundyou.biz.service.user.UserService;

@Controller(value="userContoller")
public class UserContoller {

	private Logger logger = Logger.getLogger(UserContoller.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(name="/login.do",method=RequestMethod.POST)
	public void getUserLogin(HttpServletRequest request , HttpSession session){
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserInfo userInfo = userService.getUserLogin(username, password);
		
		session.setAttribute("userInfo", userInfo);
		
		logger.info("user "+username+" login");
	}
}
