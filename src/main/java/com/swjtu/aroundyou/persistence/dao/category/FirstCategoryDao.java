package com.swjtu.aroundyou.persistence.dao.category;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.dto.category.FirstMessageCategoryDTO;

public interface FirstCategoryDao extends GenericHibernateDao<FirstMessageCategoryDTO>{

	public String NAME = "firstCategoryDao";
}
