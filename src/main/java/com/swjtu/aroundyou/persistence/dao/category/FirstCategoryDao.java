package com.swjtu.aroundyou.persistence.dao.category;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.entity.category.FirstMessageCategory;

public interface FirstCategoryDao extends GenericHibernateDao<FirstMessageCategory,Integer>{

	public String NAME = "firstCategoryDao";
}
