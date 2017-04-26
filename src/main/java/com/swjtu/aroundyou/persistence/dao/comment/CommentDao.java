package com.swjtu.aroundyou.persistence.dao.comment;

import java.util.Date;
import java.util.List;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.entity.comment.Comment;

public interface CommentDao extends GenericHibernateDao<Comment,Integer>{

	String NAME = "commentDao";
	
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
	 * @date  2017年4月4日下午3:51:31
	 * @param Integer
	 * <p>描述：根据messageNo获取评论数</p>
	 */
	public Integer getCmtCount(Integer messageNo);

    Pagination<Comment> getAllComment(Integer page, Integer pageSize);
    
    void deleteCommentsByMsg(Integer msgNo,Integer deleteId,Date current);

	void deleteComment(Integer cmtNo, Integer deleteId, Date current);
}
