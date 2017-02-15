package com.swjtu.aroundyou.persistence.dao.category.impl;

import org.springframework.stereotype.Repository;

import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;
import com.swjtu.aroundyou.persistence.dao.category.SecondCategoryDao;
import com.swjtu.aroundyou.persistence.dto.category.SecondMessageCategoryDTO;

@Repository(value=SecondCategoryDao.NAME)
public class SecondCategoryDaoImpl extends GenericHibernateDaoImpl<SecondMessageCategoryDTO> implements
		SecondCategoryDao {

}
