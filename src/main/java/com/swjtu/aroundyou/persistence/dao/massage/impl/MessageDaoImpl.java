package com.swjtu.aroundyou.persistence.dao.massage.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;
import com.swjtu.aroundyou.persistence.dao.massage.MessageDao;
import com.swjtu.aroundyou.persistence.entity.message.Message;

@Repository(value=MessageDao.NAME)
public class MessageDaoImpl extends GenericHibernateDaoImpl<Message> implements MessageDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getHotMessages() {
		
		String hql = "from Message where active = 'Y' and isHot ='Y' and deleteId = null and deleteDate = null order by createDate desc";
		
		Query query = getSession().createQuery(hql);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getAllMessages() {
		
		String hql = "from Message where active = 'Y' and deleteId = null and deleteDate = null order by createDate desc";
		
		Query query = getSession().createQuery(hql);
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getTodayMostHot() {
		
		String hql = "from Message where active = 'Y' and deleteId =null and deleteDate = null and datediff(current_date(),createDate) = 0";
		
		Query query = getSession().createQuery(hql)
				                  .setMaxResults(3);
		
		return query.list();
	}

}
