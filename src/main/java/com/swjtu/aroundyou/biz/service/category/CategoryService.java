package com.swjtu.aroundyou.biz.service.category;

import java.util.Date;
import java.util.List;

import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.entity.category.SecondMessageCategory;

public interface CategoryService {

	public String NAME ="categoryService";
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年2月25日下午12:44:39
	 * @param List<SecondMessageCategory>
	 * <p>描述：初始化页面时加载所有的分类</p>
	 */
	List<SecondMessageCategory> getAllSecondCategories();
	
	Pagination<SecondMessageCategory> getCategories(Integer pageSize,Integer page);
	
	SecondMessageCategory getCategory(Integer cateNo);
	
	void updateCategory(Integer cateNo,String cateName,String desc);
	
	void saveCategory(SecondMessageCategory category); 
	
	void deleteCategory(Integer cateNo,Integer deleteId,Date current);
}
