package com.swjtu.aroundyou.persistence.dao.category.impl;

import org.springframework.stereotype.Repository;

import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;
import com.swjtu.aroundyou.persistence.dao.category.FirstCategoryDao;
import com.swjtu.aroundyou.persistence.entity.category.FirstMessageCategory;

@Repository(value=FirstCategoryDao.NAME)
public class FirstCategoryDaoImpl extends GenericHibernateDaoImpl<FirstMessageCategory> 
            implements FirstCategoryDao {

	
}
