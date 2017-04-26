package com.swjtu.aroundyou.biz.service.comment;

import java.util.Date;
import java.util.List;

import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.entity.comment.Comment;
import com.swjtu.aroundyou.persistence.entity.message.Message;

public interface CommentService {

	String NAME = "commentService";
	
	/**
	 * 
	 * @param pageSize 
	 * @param page 
	 * @auther brotherlu
	 * @date  2017年4月2日下午9:03:31
	 * @param Comment
	 * <p>描述：在查询单个信息时，跟经验MessageNo获取其下的comment</p>
	 */
	 Pagination<Comment> getCommentByMessageNo(Integer messageNo, Integer page, Integer pageSize);
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年4月2日下午9:36:51
	 * @param Comment
	 * <p>描述：创建Comment</p>
	 */
	public void createComment(Comment comment);

	Pagination<Comment> getAllComments(Integer page, Integer pageSize);

	void deleteComment(Integer cmtNo, Integer deleteId, Date current);

	Comment getComment(Integer cmtNo);
}
