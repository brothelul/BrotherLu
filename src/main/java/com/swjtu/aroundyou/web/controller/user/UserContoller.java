package com.swjtu.aroundyou.web.controller.user;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.swjtu.aroundyou.biz.service.user.UserService;
import com.swjtu.aroundyou.biz.service.utils.AppConfigService;
import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.entity.manager.ManagerInfo;
import com.swjtu.aroundyou.persistence.entity.user.UserInfo;
import com.swjtu.aroundyou.persistence.entity.user.UserLogin;
import com.swjtu.aroundyou.persistence.entity.user.UserLoginLog;
import com.swjtu.aroundyou.persistence.entity.utils.AppConfig;
import com.swjtu.aroundyou.persistence.entity.utils.JsonResponseObject;
import com.swjtu.aroundyou.utils.MailUitl;

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
	@ResponseBody
	public String getUserLogin(HttpServletRequest request ,
			HttpSession session){
		JsonResponseObject responseObject = new JsonResponseObject();
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
			session.setAttribute("userInfo", userInfo);
			responseObject.setStatus("200");
			return JSON.toJSONString(responseObject);
		}else {
			responseObject.setStatus("401");
			return JSON.toJSONString(responseObject);
		}		
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
			@RequestParam("code")String code,
			@RequestParam("email")String email,
			HttpSession session){
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
		userInfo.setEmailAddress(email);
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
	
	@RequestMapping(value="manager/getIdUser.do",method=RequestMethod.POST)
	@ResponseBody
	public String getIdUser(@RequestParam("page")Integer page,
			@RequestParam("pageSize")Integer pageSize){
		JsonResponseObject responseObject = new JsonResponseObject();
		Pagination<UserInfo> userInfos = userService.getIdUsers(page,pageSize);
		responseObject.setStatus("200");
		responseObject.setResult(userInfos);
		return JSON.toJSONString(responseObject);
	}
	@RequestMapping(value="manager/controlUser.do",method=RequestMethod.POST)
	@ResponseBody
	public String contolUser(@RequestParam("type")Integer type,
			@RequestParam("userNo")Integer userNo,
			@RequestParam("reason")String reason,
			HttpSession session){
		JsonResponseObject responseObject = new JsonResponseObject();
		ManagerInfo managerInfo = (ManagerInfo) session.getAttribute("managerInfo");
		if (managerInfo == null) {
			responseObject.setStatus("401");
			return JSON.toJSONString(responseObject);
		}
		UserInfo userInfo = userService.getUserInfo(userNo);
		if (type == 1) { //同意
			userInfo.setPassIdentify(2);
			userInfo.setUserType(1);
			UserLogin userLogin = userService.getUserLogin(userNo);
			userLogin.setUserType(1);
			userService.updateUserLogin(userLogin);
			String content = "你的申请以通过，赶快登陆AroundYou发表消息吧。";
			MailUitl.sendMail(userInfo.getEmailAddress(), content);
		}
		if (type == 0) { //拒绝
			userInfo.setPassIdentify(3);
			userInfo.setLongDesc(reason);
			String content = "你的申请被拒绝了，原因："+reason;
			MailUitl.sendMail(userInfo.getEmailAddress(), content);
		}
		userService.saveUserInfo(userInfo);
		responseObject.setStatus("200");
		return JSON.toJSONString(responseObject);
	}
	
	@RequestMapping(value="user/saveUserChange.do",method=RequestMethod.POST)
	@ResponseBody
	public String saveUserChange(@RequestParam("photo")String photo,
			@RequestParam("email")String email,
			HttpSession session){
		JsonResponseObject responseObject = new JsonResponseObject();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		if (userInfo == null) {
			responseObject.setStatus("401");
			return JSON.toJSONString(responseObject);
		}
		
		userInfo = userService.getUserInfo(userInfo.getUserNo());
		userInfo.setEmailAddress(email);
		userInfo.setFaviconUri(photo);
		userService.saveUserInfo(userInfo);
		
		responseObject.setStatus("200");
		return JSON.toJSONString(responseObject);
	}
	
	@RequestMapping(value="user/validateUsername.do",method=RequestMethod.POST)
	@ResponseBody
	public String validateUsername(@RequestParam("username")String username){
		JsonResponseObject responseObject = new JsonResponseObject();
		if (userService.validateUsername(username)) {
			responseObject.setStatus("200");
			return JSON.toJSONString(responseObject);
		}
		responseObject.setStatus("500");
		return JSON.toJSONString(responseObject);
	}
}
