package com.swjtu.aroundyou.persistence.dao.category;

import java.util.List;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.entity.category.SecondMessageCategory;


public interface SecondCategoryDao extends GenericHibernateDao<SecondMessageCategory>{

	public String NAME = "secondCategoryDao";
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年2月25日下午12:48:01
	 * @param List<SecondMessageCategory>
	 * <p>描述：查找到所有的二级分类</p>
	 */
	List<SecondMessageCategory> findAll();
}
