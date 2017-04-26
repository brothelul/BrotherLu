package com.swjtu.aroundyou.persistence.dao.comment.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;
import com.swjtu.aroundyou.persistence.dao.comment.CommentDao;
import com.swjtu.aroundyou.persistence.entity.comment.Comment;

@Repository(value=CommentDao.NAME)
public class CommentDaoImpl extends GenericHibernateDaoImpl<Comment,Integer> implements CommentDao {

	@Override
	public Pagination<Comment> getCommentByMessageNo(
			Integer messageNo,Integer page,Integer pageSize) {		
		String hql = "from Comment where messageNo = ? and deleteId = null order by entryDate desc";
		return findPagination(hql, page, pageSize, messageNo);
	}
	
	@Override
	public Integer getCmtCount(Integer messageNo) {
		String sql = "select count(cmt_no) from comment where message_no = :messageNo";
		Query query = getSession().createSQLQuery(sql)
				                  .setParameter("messageNo", messageNo);
		if (query.list() != null && query.list().size() > 0) {
			BigInteger bigCount = (BigInteger) query.list().get(0);
			return bigCount.intValue();
		}
		return 0;
	}

	@Override
	public Pagination<Comment> getAllComment(Integer page, Integer pageSize) {
		String hql = "from Comment";
		return 	findPagination(hql, page, pageSize, null);
	}

	@Override
	public void deleteCommentsByMsg(Integer msgNo, Integer deleteId,
			Date current) {
		String hql = "update Comment set deleteId = :deleteId,deleteDate = :current where messageNo = :msgNo";
		Query query = getSession().createQuery(hql)
				                  .setParameter("deleteId", deleteId)
				                  .setParameter("current", current)
				                  .setParameter("msgNo", msgNo);
		query.executeUpdate();		
	}

	@Override
	public void deleteComment(Integer cmtNo, Integer deleteId, Date current) {
		
		String hql = "update Comment set deleteId = :deleteId,deleteDate = :current where cmtNo = :cmtNo";
		Query query = getSession().createQuery(hql)
				                  .setParameter("deleteId", deleteId)
				                  .setParameter("current", current)
				                  .setParameter("cmtNo", cmtNo);
		query.executeUpdate();
	}
}
