package com.swjtu.aroundyou.biz.service.comment.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.aroundyou.biz.service.comment.CommentService;
import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.dao.comment.CommentDao;
import com.swjtu.aroundyou.persistence.entity.comment.Comment;

@Service(value=CommentService.NAME)
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public Pagination<Comment> getCommentByMessageNo(Integer messageNo,Integer page,Integer pageSize) {
		return commentDao.getCommentByMessageNo(messageNo,page,pageSize);
	}

	@Override
	public void createComment(Comment comment) {		
		commentDao.save(comment); 
	}

	@Override
	public Pagination<Comment> getAllComments(Integer page, Integer pageSize) {
		return commentDao.getAllComment(page,pageSize);
	}

	@Override
	public void deleteComment(Integer cmtNo, Integer deleteId, Date current) {
		commentDao.deleteComment(cmtNo,deleteId,current);
	}

	@Override
	public Comment getComment(Integer cmtNo) {
		return commentDao.getById(cmtNo);
	}
}
