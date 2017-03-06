package com.swjtu.aroundyou.biz.service.message;

import java.util.List;

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
	 * @auther brotherlu
	 * @date  2017年2月26日上午12:19:14
	 * @param List<Message>
	 * <p>描述：获取所有的信息</p>
	 */
	public List<Message> findAllMessages();
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年2月26日下午9:47:41
	 * @param List<Message>
	 * <p>描述：得到今天最热门的消息</p>
	 */
	public List<Message> findTodayMostHot();
}
