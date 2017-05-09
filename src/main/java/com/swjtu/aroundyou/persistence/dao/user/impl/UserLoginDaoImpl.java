package com.swjtu.aroundyou.persistence.dao.user.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;
import com.swjtu.aroundyou.persistence.dao.user.UserLoginDao;
import com.swjtu.aroundyou.persistence.entity.user.UserLogin;

@Repository(value=UserLoginDao.NAME)
public class UserLoginDaoImpl extends GenericHibernateDaoImpl<UserLogin,Integer> implements UserLoginDao{

	@Override
	public Integer findUserLoginForLogin(String username, String password) {
		
		String hql = "from UserLogin where username = :username and password = :password";
		
		Query query = getSession().createQuery(hql)
				                  .setParameter("username", username)
				                  .setParameter("password", password);
		
		if (query.list() == null || query.list().size() == 0) {
			
			return null;
		}
		
		UserLogin userLoginDTO = (UserLogin) query.list().get(0);
		
		return userLoginDTO.getUserNo();
	}

	@Override
	public UserLogin validateUsername(String username) {
		String hql = "from UserLogin where username = :username";
		Query query = getSession().createQuery(hql)
				                  .setParameter("username", username);
		if (query.list() != null&&query.list().size() > 0) {
			return (UserLogin) query.list().get(0);
		}
		return null;
	}
}
