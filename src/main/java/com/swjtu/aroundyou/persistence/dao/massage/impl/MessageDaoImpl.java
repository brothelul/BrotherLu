package com.swjtu.aroundyou.persistence.dao.massage.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;
import com.swjtu.aroundyou.persistence.dao.massage.MessageDao;
import com.swjtu.aroundyou.persistence.entity.message.Message;

@Repository(value=MessageDao.NAME)
public class MessageDaoImpl extends GenericHibernateDaoImpl<Message,Integer> implements MessageDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getHotMessages() {
		
		String hql = "from Message where active = 2 and isHot ='Y' and deleteId = null and deleteDate = null order by createDate desc";
		
		Query query = getSession().createQuery(hql)
				                  .setMaxResults(12);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pagination<Message> getAllMessages(Integer page,Integer pageSize) {
		
		String hql = "from Message where active = 2 and deleteId = null and deleteDate = null order by createDate desc";		
		return findPagination(hql, page, pageSize, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getTodayMostHot() {
		
		String hql = "from Message where active = 2 and deleteId =null and deleteDate = null and datediff(current_date(),createDate) = 0";
		
		Query query = getSession().createQuery(hql)
				                  .setMaxResults(3);
		
		return query.list();
	}

	@Override
	public Pagination<Message> getMessagesByCat(Integer cateNo,Integer page,Integer pageSize) {
		String hql = "from Message where active=2 and deleteId = null and secondMessageCategory.categoryNo = ? order by createDate desc";
		return findPagination(hql, page, pageSize, cateNo);
	}

	@Override
	public Pagination<Message> getMessageByKeyWord(String key, Integer page,Integer pageSize) {
		String hql = "from Message where active=2 and deleteId = null and messageTitle like ?";
		return findPagination(hql, page, pageSize, key);
	}

	@Override
	public Pagination<Message> getMessagesByUser(Integer userNo, Integer page,Integer pageSize) {
		
		String hql = "from Message where active=2 and deleteId = null and userInfo.userNo = ?";
		return findPagination(hql, page, pageSize, userNo);
	}

	@Override
	public void update(Message message) {
		String hql = "update Message set viewCount = :viewCount where messageNo = :messageNo";
		Query query = getSession().createQuery(hql)
				                  .setParameter("viewCount", message.getViewCount())
				                  .setParameter("messageNo", message.getMessageNo());
		query.executeUpdate();
	}


	@Override
	public Pagination<Message> getMessageForManage(Integer page,
			Integer pageSize) {
		String hql = "from Message where deleteId = null";		
		return findPagination(hql, page, pageSize, null);
	}

	@Override
	public void deleteMsg(Integer msgNo, Integer deleteId, Date date) {
		String hql = "update Message set deleteId = :deleteId,deleteDate = :date where messageNo = :msgNo";
		Query query = getSession().createQuery(hql)
                                  .setParameter("deleteId", deleteId)
                                  .setParameter("date", date)
                                  .setParameter("msgNo", msgNo);
		query.executeUpdate();
	}


	@Override
	public void deleteMsgByCate(Integer cateNo, Integer deleteId, Date date) {
		
		String hql = "update Message set deleteId = :deleteId,deleteDate = :date where secondMessageCategory.categoryNo = :cateNo";
		Query query = getSession().createQuery(hql)
                                  .setParameter("deleteId", deleteId)
                                  .setParameter("date", date)
                                  .setParameter("cateNo", cateNo);
		query.executeUpdate();
	}

	@Override
	public void activeMessage(Integer active, Integer updateId, Date current,Integer msgNo) {
		String hql = "update Message set updateId = :updateId,updateDate = :current,active = :active where messageNo = :msgNo";
		Query query = getSession().createQuery(hql)
                                  .setParameter("updateId", updateId)
                                  .setParameter("current", current)
                                  .setParameter("active", active)
                                  .setParameter("msgNo", msgNo);
		query.executeUpdate();
	}

	@Override
	public void updateCmtCount(Integer cmtCount, Integer msgNo) {
		String hql = "update Message set cmtCount = :cmtCount where messageNo = :msgNo";
		Query query = getSession().createQuery(hql)
				                  .setParameter("cmtCount", cmtCount)
				                  .setParameter("msgNo", msgNo);
		query.executeUpdate();
	}

	@Override
	public List<Message> getMessagesByCat(Integer cateNo) {
		String hql = "from Message where deleteId != null and secondMessageCategory.categoryNo = :cateNo";
		Query query = getSession().createQuery(hql)
				                  .setParameter("cateNo", cateNo);
		return query.list();
	}
	
	@Override
	public Message getById(Serializable id) {
		String hql = "from Message where deleteId = null and deleteDate = null and active = 2 and messageNo = ?";
		Query query = getSession().createQuery(hql)
				                  .setParameter(0, id);
		if (query.list() != null && query.list().size() > 0) {
			return (Message) query.list().get(0);
		}
		return null;
	}
}
