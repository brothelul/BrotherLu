package com.swjtu.aroundyou.web.controller.category;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.swjtu.aroundyou.biz.service.category.CategoryService;

@Controller(value="categoryController")
public class CategoryController {

	private Logger logger = Logger.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
}
