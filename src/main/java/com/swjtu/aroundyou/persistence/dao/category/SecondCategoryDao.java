package com.swjtu.aroundyou.persistence.dao.category;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.dto.category.SecondMessageCategoryDTO;

public interface SecondCategoryDao extends GenericHibernateDao<SecondMessageCategoryDTO>{

	public String NAME = "secondCategoryDao";
}
