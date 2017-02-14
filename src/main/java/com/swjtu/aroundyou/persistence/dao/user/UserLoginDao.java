package com.swjtu.aroundyou.persistence.dao.user;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.dto.user.UserIdDTO;
import com.swjtu.aroundyou.persistence.dto.user.UserLoginDTO;

public interface UserLoginDao extends GenericHibernateDao<UserLoginDTO>{

	public String NAME = "UserLoginDao";
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年2月9日下午8:26:43
	 * @param UserLoginDTO
	 * <p>描述：判断用户登录，成功则获取到id</p>
	 */
	public UserIdDTO findUserLoginForLogin(String username, String password); 
}
