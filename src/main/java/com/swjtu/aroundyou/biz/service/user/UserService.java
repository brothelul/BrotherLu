package com.swjtu.aroundyou.biz.service.user;

import com.swjtu.aroundyou.persistence.entity.user.UserInfo;

public interface UserService {

	String NAME = "userService";
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年2月9日下午8:23:05
	 * @param UserInfo
	 * <p>描述：用户登录后获取用户的基本信息</p>
	 */
	public UserInfo getUserLogin(String username,String password);
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年2月25日上午10:58:57
	 * @param UserInfo
	 * <p>描述：登录时存储用户的登录IP和时间</p>
	 */
	public void saveUserLoginInfo(UserInfo userInfo);
}
