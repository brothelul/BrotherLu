package com.swjtu.aroundyou.persistence.dao.massage;

import java.util.Date;
import java.util.List;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.entity.message.Message;

public interface MessageDao extends GenericHibernateDao<Message,Integer> {

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
	 * @param pageSize 
	 * @param page 
	 * @auther brotherlu
	 * @date  2017年2月26日上午12:20:32
	 * @param List<Message>
	 * <p>描述：获取所有的信息</p>
	 */
	Pagination<Message> getAllMessages(Integer page, Integer pageSize);
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年4月4日上午11:37:04
	 * @param List<Message>
	 * <p>描述：获取当日最热的新闻</p>
	 */
	List<Message> getTodayMostHot();
	
	/**
	 * 
	 * @param pageSize 
	 * @param page 
	 * @auther brotherlu
	 * @date  2017年4月4日上午11:37:56
	 * @param List<Message>
	 * <p>描述：根据分类查询message</p>
	 */
	Pagination<Message> getMessagesByCat(Integer catNo, Integer page, Integer pageSize);
	
	/**
	 * 
	 * @param pageSize 
	 * @auther brotherlu
	 * @date  2017年4月12日下午9:53:14
	 * @param List<Message>
	 * <p>描述：根据key查询</p>
	 */
	Pagination<Message> getMessageByKeyWord(String key,Integer page, Integer pageSize);
	
	/**
	 * 
	 * @param pageSize 
	 * @param type 
	 * @auther brotherlu
	 * @date  2017年4月18日下午3:08:21
	 * @param List<Message>
	 * <p>描述：根据UserNo获取message</p>
	 */
	Pagination<Message> getMessagesByUser(Integer userNo,Integer page, Integer pageSize, Integer type);
	
	Pagination<Message> getMessageForManage(Integer page,Integer pageSize);
	
	void deleteMsg(Integer msgNo,Integer deleteId,Date date);
	
	void deleteMsgByCate(Integer cateNo,Integer deleteId,Date date);

	void activeMessage(Integer active, Integer updateId, Date current,Integer msgNo);

	void updateCmtCount(Integer cmtCount, Integer msgNo);

	List<Message> getMessagesByCat(Integer cateNo);

	Message getMsg(Integer msgNo);
}
