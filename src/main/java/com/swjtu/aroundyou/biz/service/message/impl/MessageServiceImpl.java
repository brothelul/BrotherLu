package com.swjtu.aroundyou.biz.service.message.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.aroundyou.biz.service.message.MessageService;
import com.swjtu.aroundyou.persistence.dao.massage.MessageDao;
import com.swjtu.aroundyou.persistence.entity.message.Message;

@Service(value=MessageService.NAME)
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;

	@Override
	public List<Message> findHotMessages() {
		
		List<Message> sliderMessages = messageDao.getHotMessages();
		
		if (sliderMessages == null) {
			
			return new ArrayList<>();
		}
		
		return sliderMessages;
	}

	@Override
	public List<Message> findAllMessages() {
		
		List<Message> sliderMessages = messageDao.getAllMessages();
		
		if (sliderMessages == null) {
			
			return new ArrayList<>();
		}
		
		return sliderMessages;
	}

	@Override
	public List<Message> findTodayMostHot() {
		
		List<Message> sliderMessages = messageDao.getTodayMostHot();
		
		if (sliderMessages == null) {
			
			return new ArrayList<>();
		}
		
		return sliderMessages;
	}
}
