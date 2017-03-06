package com.swjtu.aroundyou.web.controller.user;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swjtu.aroundyou.biz.service.user.UserService;
import com.swjtu.aroundyou.persistence.entity.user.UserInfo;

@Controller
public class UserContoller {

	private Logger logger = Logger.getLogger(UserContoller.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login.do",method=RequestMethod.POST)
	public String getUserLogin(HttpServletRequest request , HttpSession session){
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserInfo userInfo = userService.getUserLogin(username, password);
		
		if (userInfo != null) {
			
			userInfo.setLastLoginIP(request.getRemoteAddr());
			userInfo.setLastLoginDate(new Date());
			
			userService.saveUserLoginInfo(userInfo);
			
			logger.info("user "+username+" login");
		}
		
		session.setAttribute("userInfo", userInfo);
		
		return "redirect:index.jsp";
	}
}
