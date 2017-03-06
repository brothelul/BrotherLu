package com.swjtu.aroundyou.persistence.dao.category.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;
import com.swjtu.aroundyou.persistence.dao.category.SecondCategoryDao;
import com.swjtu.aroundyou.persistence.entity.category.SecondMessageCategory;

@Repository(value=SecondCategoryDao.NAME)
public class SecondCategoryDaoImpl extends GenericHibernateDaoImpl<SecondMessageCategory> implements
		SecondCategoryDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SecondMessageCategory> findAll() {
		
		String hql = "from SecondMessageCategory";
		
		Query query = getSession().createQuery(hql);
		
		return query.list();
	}

}
