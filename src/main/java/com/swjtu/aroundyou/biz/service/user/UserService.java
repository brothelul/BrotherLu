package com.swjtu.aroundyou.biz.service.user;

import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.entity.user.UserInfo;
import com.swjtu.aroundyou.persistence.entity.user.UserLogin;
import com.swjtu.aroundyou.persistence.entity.user.UserLoginLog;

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
	public void updateUserLoginInfo(UserInfo userInfo);
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年3月30日下午9:11:23
	 * @param void
	 * <p>描述：登录时存储登录信息Log</p>
	 */
	public void saveUserLoginLog(UserLoginLog userLoginLog);
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年4月3日下午12:06:52
	 * @param UserInfo
	 * <p>描述：根据ID获取userInfo</p>
	 */
	public UserInfo getUserInfo(Integer userNo);
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年4月3日下午6:36:44
	 * @param void
	 * <p>描述：用户注册</p>
	 */
	public Integer saveUserLogin(UserLogin userLogin);
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年4月3日下午6:39:15
	 * @param void
	 * <p>描述：注册时创建账户</p>
	 */
	public void saveUserInfo(UserInfo userInfo);
	
	Pagination<UserInfo> getAllUsers(Integer page,Integer pageSize);
}
