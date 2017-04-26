package com.swjtu.aroundyou.web.controller.user;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.swjtu.aroundyou.biz.service.user.UserService;
import com.swjtu.aroundyou.biz.service.utils.AppConfigService;
import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.entity.user.UserInfo;
import com.swjtu.aroundyou.persistence.entity.user.UserLogin;
import com.swjtu.aroundyou.persistence.entity.user.UserLoginLog;
import com.swjtu.aroundyou.persistence.entity.utils.AppConfig;
import com.swjtu.aroundyou.persistence.entity.utils.JsonResponseObject;

@Controller
public class UserContoller {

	private Logger logger = Logger.getLogger(UserContoller.class);
	private final String APPCAT ="FAVICON";
	private final String APPNAME = "DFT";
	@Autowired
	private UserService userService;
	@Autowired
	private AppConfigService appConfigService;
	
	@RequestMapping(value="user/login.do",method=RequestMethod.POST)
	public String getUserLogin(HttpServletRequest request , HttpSession session){
		
		UserLoginLog userLoginLog = new UserLoginLog();
		Date current = new Date();
		String loginIP = request.getRemoteAddr();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserInfo userInfo = userService.getUserLogin(username, password);
		
		if (userInfo != null) {
			userInfo.setLastLoginIP(loginIP);
			userInfo.setLastLoginDate(current);
			userService.updateUserLoginInfo(userInfo);
			
			userLoginLog.setUserNo(userInfo.getUserNo());
			userLoginLog.setUserType(userInfo.getUserType());
			userLoginLog.setLoginIP(loginIP);
			userLoginLog.setLoginDate(current);
			userService.saveUserLoginLog(userLoginLog);
			
			logger.info("user "+username+" login");
		}		
		session.setAttribute("userInfo", userInfo);
		
		return "redirect:index.jsp";
	}
	
	@RequestMapping(value="user/logout.do",method=RequestMethod.GET)
	public String userLogout(HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");		
		if (userInfo != null) {
			logger.info("user "+userInfo.getUsername()+" logout");
		}
		session.removeAttribute("userInfo");
		return "redirect:index.jsp";
	}
	
	@RequestMapping(value="user/registUser.do",method=RequestMethod.POST)
	@ResponseBody
	public String userRegist(@RequestParam("username")String username,
			@RequestParam("password")String password,
			@RequestParam("code")String code,HttpSession session){
		JsonResponseObject jsonResponseObject = new JsonResponseObject();
		Date current = new Date();
		if (username == null||username.length() < 2|| username.length()>8) {
			jsonResponseObject.setStatus("401");
			return JSON.toJSONString(jsonResponseObject);
		}
		if (password == null || password.length() >30||password.length()<6) {
			jsonResponseObject.setStatus("401");
			return JSON.toJSONString(jsonResponseObject);
		}		
		if (code==null||!code.equalsIgnoreCase((String)session.getAttribute("checkcode"))) {
			jsonResponseObject.setStatus("401");
			return JSON.toJSONString(jsonResponseObject);
		}
		
		UserLogin userLogin = new UserLogin();
		userLogin.setUserType(0);
		userLogin.setUsername(username);
		userLogin.setPassword(password);
		userLogin.setCreateId(-1);
		userLogin.setCreateDate(current);
		Integer userNo = userService.saveUserLogin(userLogin);
		
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(username);
		userInfo.setUserNo(userNo);
		userInfo.setUserType(0);
		userInfo.setPassIdentify(0);
		userInfo.setFriend(5);
		String defaultFavicon = appConfigService.getAppConfigValue(APPCAT, APPNAME);
		userInfo.setFaviconUri(defaultFavicon);
		userService.saveUserInfo(userInfo);
		
		jsonResponseObject.setStatus("200");
		return JSON.toJSONString(jsonResponseObject);
	}
	
	@RequestMapping(value="toUserPage.do",method=RequestMethod.GET)
	public String toUserPage(HttpSession session,@RequestParam("userNo") Integer userNo){
		UserInfo userInfo = userService.getUserInfo(userNo);
		session.setAttribute("author", userInfo);
		return "user/user_index";
	}
	
	@RequestMapping(value="manager/getUsers.do",method=RequestMethod.POST)
	@ResponseBody
	public String getUsers(@RequestParam("page")Integer page,
			@RequestParam("pageSize")Integer pageSize){
		JsonResponseObject responseObject = new JsonResponseObject();
		Pagination<UserInfo> userInfos = userService.getAllUsers(page, pageSize);
		responseObject.setStatus("200");
		responseObject.setResult(userInfos);
		return JSON.toJSONString(responseObject);
	}
}
