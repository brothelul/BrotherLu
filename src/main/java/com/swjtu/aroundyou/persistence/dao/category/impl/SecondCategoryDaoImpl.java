package com.swjtu.aroundyou.persistence.dao.category.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;
import com.swjtu.aroundyou.persistence.dao.category.SecondCategoryDao;
import com.swjtu.aroundyou.persistence.entity.category.SecondMessageCategory;

@Repository(value=SecondCategoryDao.NAME)
public class SecondCategoryDaoImpl extends GenericHibernateDaoImpl<SecondMessageCategory,Integer> implements
		SecondCategoryDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SecondMessageCategory> findAll() {
		
		String hql = "from SecondMessageCategory where deleteId = null and deleteDate =null";
		
		Query query = getSession().createQuery(hql);
		
		return query.list();
	}

	@Override
	public Pagination<SecondMessageCategory> getCategories(Integer pageSize,
			Integer page) {
		String hql = "from SecondMessageCategory where deleteId = null";
		Pagination<SecondMessageCategory> categories = findPagination(hql, page, pageSize, null);		
		return categories;
	}

	@Override
	public void updateCategory(Integer cateNo,String cateName, String desc) {
		String hql = "update SecondMessageCategory set categoryDesc = :desc,categoryName = :cateName where categoryNo = :cateNo";
		Query query = getSession().createQuery(hql)
				                  .setParameter("cateNo", cateNo)
				                  .setParameter("desc", desc)
				                  .setParameter("cateName", cateName);
		query.executeUpdate();
	}

	@Override
	public void deleteCategory(Integer cateNo, Integer deleteId, Date current) {
		String hql = "update SecondMessageCategory set deleteId = :deleteId,deleteDate = :current where categoryNo = :cateNo";
		Query query = getSession().createQuery(hql)
				                  .setParameter("deleteId", deleteId)
				                  .setParameter("current", current)
				                  .setParameter("cateNo", cateNo);
		query.executeUpdate();
		
	}

}
