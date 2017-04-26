package com.swjtu.aroundyou.web.controller.manager;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.swjtu.aroundyou.biz.service.manager.ManagerService;
import com.swjtu.aroundyou.biz.service.user.UserService;
import com.swjtu.aroundyou.persistence.dao.user.UserLoginDao;
import com.swjtu.aroundyou.persistence.entity.manager.ManagerInfo;
import com.swjtu.aroundyou.persistence.entity.manager.ManagerLogin;
import com.swjtu.aroundyou.persistence.entity.manager.ManagerLoginLog;
import com.swjtu.aroundyou.persistence.entity.user.UserLogin;
import com.swjtu.aroundyou.persistence.entity.utils.JsonResponseObject;

@Controller
@RequestMapping(value="manager/")
public class ManagerController {

	@Autowired
	private ManagerService managerService;
	
	@RequestMapping(value="login.do",method=RequestMethod.POST)
	@ResponseBody
	public String managerLogin(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpServletRequest request){
		JsonResponseObject responseObject = new JsonResponseObject();
		Date current = new Date();
		ManagerLogin managerLogin = managerService.getManagerLogin(username, password);
		if (managerLogin == null) {
			responseObject.setStatus("401");
			return JSON.toJSONString(responseObject);
		}
		Integer managerNo = managerLogin.getManagerNo();
		ManagerInfo managerInfo = managerService.getManagerInfo(managerNo);		
		request.getSession().setAttribute("managerInfo", managerInfo);
		
		String ip = request.getRemoteAddr();
		ManagerLoginLog managerLoginLog = new ManagerLoginLog();
		managerLoginLog.setManagerNo(managerNo);
		managerLoginLog.setLoginIP(ip);
		managerLoginLog.setLoginDate(current);
		managerService.saveManagerLoginLog(managerLoginLog);
		
		responseObject.setStatus("200");
		getLoginLog(request.getSession());
		return JSON.toJSONString(responseObject);
	}
	
	@RequestMapping(value="logout.do",method=RequestMethod.GET)
	public String ManagerLogout(HttpSession session){
		session.removeAttribute("managerInfo");
		session.removeAttribute("loginLog");
		return "redirect:login.jsp";
	}
	
	private void getLoginLog(HttpSession session){

		ManagerInfo managerInfo = (ManagerInfo) session.getAttribute("managerInfo");
		Integer managerNo = managerInfo.getManagerNo();
		ManagerLoginLog loginLog = managerService.getManagerLoginLog(managerNo);
		session.setAttribute("loginLog", loginLog);

	}
}
