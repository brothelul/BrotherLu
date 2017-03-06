package com.swjtu.aroundyou.biz.service.category.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.aroundyou.biz.service.category.CategoryService;
import com.swjtu.aroundyou.persistence.dao.category.FirstCategoryDao;
import com.swjtu.aroundyou.persistence.dao.category.SecondCategoryDao;
import com.swjtu.aroundyou.persistence.entity.category.SecondMessageCategory;

@Service(value=CategoryService.NAME)
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private FirstCategoryDao firstCategoryDao;
	@Autowired
	private SecondCategoryDao secondCategoryDao;
	@Override
	public List<SecondMessageCategory> getAllSecondCategories() {
			
		List<SecondMessageCategory> list =  secondCategoryDao.findAll();
		
		if (list == null) {
			
			return new ArrayList<SecondMessageCategory>();
		}
		
		return list;
	}
}
