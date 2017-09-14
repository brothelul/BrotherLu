package com.swjtu.aroundyou.web.controller.category;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.swjtu.aroundyou.biz.service.category.CategoryService;
import com.swjtu.aroundyou.biz.service.manager.ManagerService;
import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.entity.category.SecondMessageCategory;
import com.swjtu.aroundyou.persistence.entity.manager.ManagerInfo;
import com.swjtu.aroundyou.persistence.entity.utils.JsonResponseObject;

@Controller
public class CategoryController {

	private Logger logger = Logger.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ManagerService managerService;
	
	@RequestMapping(value="user/loadCategory.do")
	@ResponseBody
	public String initCategory(){		
		List<SecondMessageCategory> list = categoryService.getAllSecondCategories();							
		return JSON.toJSONString(list);	
	}
	
	@RequestMapping(value="manager/loadCategory.do",method=RequestMethod.POST)
	@ResponseBody
	public String loadCateGory(@RequestParam("page")Integer page,
			@RequestParam("pageSize")Integer pageSize){ //暂定为一页十个
		JsonResponseObject responseObject = new JsonResponseObject();
		Pagination<SecondMessageCategory> categories = categoryService.getCategories(pageSize, page);
		if (categories.getItems() != null && categories.getItems().size() > 0) {
			for (SecondMessageCategory category : categories.getItems()) {
				Integer managerNo = category.getCreateId();				
				ManagerInfo managerInfo = managerService.getManagerInfo(managerNo);
				category.setManagerInfo(managerInfo);
			}
		}
		responseObject.setStatus("200");
		responseObject.setResult(categories);
		return JSON.toJSONString(responseObject,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@RequestMapping(value="manager/getCategory.do",method=RequestMethod.POST)
	@ResponseBody
	public String getCategory(@RequestParam("cateNo")Integer cateNo){
		JsonResponseObject responseObject = new JsonResponseObject();
		SecondMessageCategory category = categoryService.getCategory(cateNo);
		
		Integer managerNo = category.getCreateId();				
		ManagerInfo managerInfo = managerService.getManagerInfo(managerNo);
		category.setManagerInfo(managerInfo);
		
		responseObject.setStatus("200");
		responseObject.setResult(category);
		return JSON.toJSONString(responseObject);
	}
	
	@RequestMapping(value="manager/saveCategory.do",method=RequestMethod.POST)
	@ResponseBody
	public String saveCategory(@RequestParam("cateNo")Integer cateNo
			,@RequestParam("desc")String desc
			,@RequestParam("cateName")String cateName){
		JsonResponseObject responseObject = new JsonResponseObject();
		categoryService.updateCategory(cateNo, cateName,desc);
		responseObject.setStatus("200");
		return JSON.toJSONString(responseObject);
	}
	
	@RequestMapping(value="manager/saveNewCategory.do",method=RequestMethod.POST)
	@ResponseBody	
	public String saveNewCategory(@RequestParam("cateName")String cateName,
			@RequestParam("cateDesc")String desc,
			HttpSession session){
		JsonResponseObject responseObject = new JsonResponseObject();
		Date current = new Date();
		SecondMessageCategory category = new SecondMessageCategory();
		ManagerInfo managerInfo = (ManagerInfo) session.getAttribute("managerInfo");
		Integer createId = managerInfo.getManagerNo();
		category.setCategoryName(cateName);
		category.setCategoryDesc(desc);
		category.setCreateId(createId);
		category.setCreateDate(current);
		categoryService.saveCategory(category);
		responseObject.setStatus("200");
		return JSON.toJSONString(responseObject);
	}

	@RequestMapping(value="manager/deleteCategory.do",method=RequestMethod.GET)	
	public String deleteCategory(@RequestParam("cateNo")Integer cateNo,
			HttpSession session){		
		ManagerInfo managerInfo = (ManagerInfo) session.getAttribute("managerInfo");
		Integer deleteId = managerInfo.getManagerNo();
		Date current = new Date();
		categoryService.deleteCategory(cateNo, deleteId, current);		
		return "redirect:right.html";
	}
}
