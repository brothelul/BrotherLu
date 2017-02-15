package com.swjtu.aroundyou.biz.service.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.aroundyou.biz.service.category.CategoryService;
import com.swjtu.aroundyou.persistence.dao.category.FirstCategoryDao;
import com.swjtu.aroundyou.persistence.dao.category.SecondCategoryDao;

@Service(value=CategoryService.NAME)
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private FirstCategoryDao firstCategoryDao;
	@Autowired
	private SecondCategoryDao secondCategoryDao;
}
