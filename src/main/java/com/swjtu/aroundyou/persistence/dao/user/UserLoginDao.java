package com.swjtu.aroundyou.persistence.dao.user;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.entity.user.UserLogin;

public interface UserLoginDao extends GenericHibernateDao<UserLogin,Integer>{

	public String NAME = "UserLoginDao";
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年2月9日下午8:26:43
	 * @param UserLogin
	 * <p>描述：判断用户登录，成功则获取到id</p>
	 */
	Integer findUserLoginForLogin(String username, String password);

	UserLogin validateUsername(String username); 
}
