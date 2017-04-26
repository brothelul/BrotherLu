package com.swjtu.aroundyou.persistence.dao.manager.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;
import com.swjtu.aroundyou.persistence.dao.manager.ManagerLoginLogDao;
import com.swjtu.aroundyou.persistence.entity.manager.ManagerLoginLog;

@Repository(value=ManagerLoginLogDao.NAME)
public class ManagerLoginLogDaoImpl extends GenericHibernateDaoImpl<ManagerLoginLog, Integer>
		implements ManagerLoginLogDao {

	@Override
	public ManagerLoginLog getManagerLoginLog(Integer managerNo) {
		String hql = "from ManagerLoginLog where managerNo = :managerNo order by logNo desc";
		Query query = getSession().createQuery(hql)
				                  .setParameter("managerNo", managerNo)
				                  .setMaxResults(1)
				                  .setFirstResult(1);
		if (query.list() == null || query.list().size() < 1) {
			return null;
		}
		return (ManagerLoginLog) query.list().get(0);
	}
}
