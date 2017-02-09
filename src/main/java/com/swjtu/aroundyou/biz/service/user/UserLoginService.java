package com.swjtu.aroundyou.biz.service.user;

import com.swjtu.aroundyou.biz.entity.user.UserLogin;

public interface UserLoginService {

	String NAME = "userLoginService";
	public UserLogin getUserLogin(String username,String password);
}
