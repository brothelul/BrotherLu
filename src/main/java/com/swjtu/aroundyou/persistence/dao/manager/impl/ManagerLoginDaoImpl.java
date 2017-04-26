package com.swjtu.aroundyou.persistence.dao.manager.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;
import com.swjtu.aroundyou.persistence.dao.manager.ManagerLoginDao;
import com.swjtu.aroundyou.persistence.entity.manager.ManagerLogin;

@Repository(value=ManagerLoginDao.NAME)
public class ManagerLoginDaoImpl extends GenericHibernateDaoImpl<ManagerLogin, Integer>
		implements ManagerLoginDao {

	@Override
	public ManagerLogin getManagerLogin(String managerName, String password) {
		String hql = "from ManagerLogin where managerName = :managerName and password = :password";
		Query query = getSession().createQuery(hql)
				                  .setParameter("managerName", managerName)
				                  .setParameter("password", password);
		if (query.list() != null && query.list().size() > 0) {
			return (ManagerLogin) query.list().get(0);
		}
		return null;
	}

}
