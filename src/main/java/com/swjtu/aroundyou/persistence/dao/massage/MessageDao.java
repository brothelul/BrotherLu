package com.swjtu.aroundyou.persistence.dao.massage;

import java.util.List;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.entity.message.Message;

public interface MessageDao extends GenericHibernateDao<Message> {

	String NAME = "messageDao";
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年2月25日下午9:06:03
	 * @param List<Message>
	 * <p>描述：用于旋转木马和热门的展示</p>
	 */	
	List<Message> getHotMessages();
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年2月26日上午12:20:32
	 * @param List<Message>
	 * <p>描述：获取所有的信息</p>
	 */
	List<Message> getAllMessages();
	
	List<Message> getTodayMostHot();
}
