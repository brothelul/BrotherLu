package com.swjtu.aroundyou.persistence.dao.user.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;
import com.swjtu.aroundyou.persistence.dao.user.UserLoginDao;
import com.swjtu.aroundyou.persistence.dto.user.UserIdDTO;
import com.swjtu.aroundyou.persistence.dto.user.UserLoginDTO;

@Repository(value=UserLoginDao.NAME)
public class UserLoginDaoImpl extends GenericHibernateDaoImpl<UserLoginDTO> implements UserLoginDao{

	@Override
	public UserIdDTO findUserLoginForLogin(String username, String password) {
		
		String hql = "from UserLoginDTO where username = :username and password = :password";
		
		Query query = getSession().createQuery(hql)
				                  .setParameter("username", username)
				                  .setParameter("password", password);
		
		if (query.list() == null || query.list().size() == 0) {
			
			return null;
		}
		
		UserLoginDTO userLoginDTO = (UserLoginDTO) query.list().get(0);
		
		return userLoginDTO.getId();
	}

}
