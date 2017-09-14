package com.swjtu.aroundyou.biz.service.category.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.aroundyou.biz.service.category.CategoryService;
import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.dao.category.FirstCategoryDao;
import com.swjtu.aroundyou.persistence.dao.category.SecondCategoryDao;
import com.swjtu.aroundyou.persistence.dao.comment.CommentDao;
import com.swjtu.aroundyou.persistence.dao.massage.MessageDao;
import com.swjtu.aroundyou.persistence.entity.category.SecondMessageCategory;
import com.swjtu.aroundyou.persistence.entity.message.Message;

@Service(value=CategoryService.NAME)
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private FirstCategoryDao firstCategoryDao;
	@Autowired
	private SecondCategoryDao secondCategoryDao;
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private CommentDao commentDao;
	@Override
	public List<SecondMessageCategory> getAllSecondCategories() {
			
		List<SecondMessageCategory> list =  secondCategoryDao.findAll();
		
		if (list == null) {
			
			return new ArrayList<SecondMessageCategory>();
		}
		
		return list;
	}
	@Override
	public Pagination<SecondMessageCategory> getCategories(Integer pageSize,
			Integer page) {		
		return secondCategoryDao.getCategories(pageSize, page);
	}
	@Override
	public SecondMessageCategory getCategory(Integer cateNo) {
		return secondCategoryDao.getById(cateNo);
	}
	@Override
	public void updateCategory(Integer cateNo, String cateName,String desc) {
		secondCategoryDao.updateCategory(cateNo, cateName,desc);	
	}
	@Override
	public void saveCategory(SecondMessageCategory category) {
		secondCategoryDao.save(category);
	}
	@Override
	public void deleteCategory(Integer cateNo, Integer deleteId, Date current) {
		secondCategoryDao.deleteCategory(cateNo, deleteId, current);
		messageDao.deleteMsg(cateNo, deleteId, current);
		List<Message> messages = messageDao.getMessagesByCat(cateNo);
		if (messages != null && messages.size() > 0) {
			for (Message message : messages) {
				Integer msgNo = message.getMessageNo();
				commentDao.deleteCommentsByMsg(msgNo, deleteId, current);
			}
		}
	}
}
