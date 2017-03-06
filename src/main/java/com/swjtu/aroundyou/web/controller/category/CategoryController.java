package com.swjtu.aroundyou.web.controller.category;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.swjtu.aroundyou.biz.service.category.CategoryService;
import com.swjtu.aroundyou.persistence.entity.category.SecondMessageCategory;

@Controller
public class CategoryController {

	private Logger logger = Logger.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="loadCategory.do")
	@ResponseBody
	public String initCategory(){
		
		List<SecondMessageCategory> list = categoryService.getAllSecondCategories();		
			
		String userInfoJson="";
		try {
			
			userInfoJson = JSON.toJSONString(list);
		} catch (Exception e) {
			
			logger.warn("get second category json failed");
		}
		
		return userInfoJson;		
	}
}
