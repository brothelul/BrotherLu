package com.swjtu.aroundyou.biz.service.manager;

import com.swjtu.aroundyou.persistence.entity.manager.ManagerInfo;
import com.swjtu.aroundyou.persistence.entity.manager.ManagerLogin;
import com.swjtu.aroundyou.persistence.entity.manager.ManagerLoginLog;

public interface ManagerService {
	String NAME = "managerService";
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年4月19日下午3:42:45
	 * @param UserLogin
	 * <p>描述：manager登录校验</p>
	 */
	ManagerLogin getManagerLogin(String managerName,String password);
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年4月19日下午3:51:23
	 * @param ManagerInfo
	 * <p>描述：登录成功获取manager信息</p>
	 */
	ManagerInfo getManagerInfo(Integer managerNo);
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年4月19日下午3:57:46
	 * @param void
	 * <p>描述：保持manager登录的log</p>
	 */
	void saveManagerLoginLog(ManagerLoginLog managerLoginLog);
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年4月20日下午1:36:05
	 * @param ManagerInfo
	 * <p>描述：获取最近一次登录的信息</p>
	 */
	ManagerLoginLog getManagerLoginLog(Integer managerNo);
}
