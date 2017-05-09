package com.swjtu.aroundyou.biz.service.message;

import java.util.Date;
import java.util.List;

import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.entity.message.Message;

public interface MessageService {

	String NAME = "messageService";
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年2月25日下午9:09:26
	 * @param List<Message>
	 * <p>描述：显示热门消息</p>
	 */
	public List<Message> findHotMessages();
	
	/**
	 * 
	 * @param pageSize 
	 * @param page 
	 * @auther brotherlu
	 * @date  2017年2月26日上午12:19:14
	 * @param List<Message>
	 * <p>描述：获取所有的信息</p>
	 */
	Pagination<Message> findAllMessages(Integer page, Integer pageSize);
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年2月26日下午9:47:41
	 * @param List<Message>
	 * <p>描述：得到今天最热门的消息</p>
	 */
	public List<Message> findTodayMostHot();
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年4月2日下午3:29:35
	 * @param Message
	 * <p>描述：获取message</p>
	 */
	public Message getMessage(Integer messageNo);
	
	/**
	 * 
	 * @param cateNo 
	 * @param pageSize 
	 * @auther brotherlu
	 * @date  2017年4月4日上午11:35:58
	 * @param List<Message>
	 * <p>描述：根据CatNo获取message</p>
	 */
	Pagination<Message> getMessagesByCat(Integer cateNo, Integer page, Integer pageSize);
	
	/**
	 * 
	 * @param pageSize 
	 * @auther brotherlu
	 * @date  2017年4月12日下午9:39:30
	 * @param List<Message>
	 * <p>描述：根据关键字查询</p>
	 */
	 Pagination<Message> searchMessages(String keyWord,Integer page, Integer pageSize);
	
	/**
	 * 
	 * @param pageSize 
	 * @param type 
	 * @auther brotherlu
	 * @date  2017年4月18日下午3:06:17
	 * @param List<Message>
	 * <p>描述：根据UserNo查找用户</p>
	 */
	Pagination<Message> getMessagesByUser(Integer userNo,Integer page, Integer pageSize, Integer type);
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年4月21日下午4:21:46
	 * @param Pagination<Message>
	 * <p>描述：用于manage页面的message，获取所有除去已删除的message</p>
	 */
	public Pagination<Message> getMessgaesForManage(Integer page,Integer pageSize);
	
	void deleteMsg(Integer msgNo,Integer deleteId,Date date);

	void activeMessage(Integer type, Integer updateId, Date current,Integer msgNo);

	void updateCmtCount(Integer cmtCount,Integer msgNo);

	void changeHot(Integer msgNo, Integer updateId, Date current);

	Message getMsg(Integer msgNo);

	void saveMessage(Message message);
}
