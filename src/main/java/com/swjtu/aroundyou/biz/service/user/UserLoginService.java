package com.swjtu.aroundyou.biz.service.user;

import com.swjtu.aroundyou.biz.entity.user.UserInfo;

public interface UserLoginService {

	String NAME = "userLoginService";
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年2月9日下午8:23:05
	 * @param UserInfo
	 * <p>描述：用户登录后获取用户的基本信息</p>
	 */
	public UserInfo getUserLogin(String username,String password);
}
