package com.swjtu.aroundyou.biz.service.message.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.aroundyou.biz.service.message.MessageService;
import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.dao.comment.CommentDao;
import com.swjtu.aroundyou.persistence.dao.massage.MessageDao;
import com.swjtu.aroundyou.persistence.entity.message.Message;
import com.swjtu.aroundyou.utils.StringUtil;

@Service(value=MessageService.NAME)
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;
	@Autowired
	private CommentDao commentDao;

	@Override
	public List<Message> findHotMessages() {		
		List<Message> sliderMessages = messageDao.getHotMessages();
		
		if (sliderMessages == null) {			
			return new ArrayList<>();
		}
		for (Message message : sliderMessages) {
			Integer messageNo = message.getMessageNo();
			int count = commentDao.getCmtCount(messageNo);
			message.setCmtCount(count);
		}
		return sliderMessages;
	}

	@Override
	public List<Message> findTodayMostHot() {		
		List<Message> sliderMessages = messageDao.getTodayMostHot();
		
		if (sliderMessages == null) {			
			return new ArrayList<>();
		}
		for (Message message : sliderMessages) {
			Integer messageNo = message.getMessageNo();
			int count = commentDao.getCmtCount(messageNo);
			message.setCmtCount(count);
		}
		return sliderMessages;
	}
	
	@Override
	public Message getMessage(Integer messageNo) {
		Message message = messageDao.getById(messageNo);
		
		Integer viewCount = message.getViewCount();
		viewCount++;
		message.setViewCount(viewCount);
		messageDao.update(message);
		return message;
	}

	@Override
	public Pagination<Message> getMessagesByCat(Integer cateNo,Integer page,Integer pageSize) {
		return messageDao.getMessagesByCat(cateNo,page,pageSize);
	}

	@Override
	public Pagination<Message> searchMessages(String keyWord, Integer page,Integer pageSize) {
		String key = StringUtil.getSearchKey(keyWord);
		Pagination<Message> messages = messageDao.getMessageByKeyWord(key, page,pageSize);
		return messages;
	}

	@Override
	public Pagination<Message> getMessagesByUser(Integer userNo,Integer page,Integer pageSize,Integer type) {		
		Pagination<Message> messages = messageDao.getMessagesByUser(userNo, page,pageSize,type);
		return messages;
	}

	@Override
	public Pagination<Message> getMessgaesForManage(Integer page,
			Integer pageSize) {
		return messageDao.getMessageForManage(page, pageSize);
	}

	@Override
	public void deleteMsg(Integer msgNo, Integer deleteId, Date date) {
		messageDao.deleteMsg(msgNo, deleteId, date);
		commentDao.deleteCommentsByMsg(msgNo, deleteId, date);
	}

	@Override
	public void activeMessage(Integer type, Integer updateId, Date current,Integer msgNo) {
		Integer active = 1; // 状态为 待提交
		if (type == 1) { //状态为 已审核
			active = 2;
		}else if (type == 0) {
			active = 0;
		}
		messageDao.activeMessage(active,updateId,current,msgNo);
	}

	@Override
	public Pagination<Message> findAllMessages(Integer page, Integer pageSize) {	
		return messageDao.getAllMessages(page,pageSize);
	}

	@Override
	public void updateCmtCount(Integer cmtCount,Integer msgNo) {
		messageDao.updateCmtCount(cmtCount,msgNo);
	}

	@Override
	public void changeHot(Integer msgNo,Integer updateId, Date current) {
		Message message = messageDao.getById(msgNo);
		if (message == null) {
			throw new RuntimeException("message#"+msgNo+" doesn't exsit or isn't active");
		}
		Character hot = message.getIsHot();
		if (hot.equals('N')) {
			hot = 'Y';
		}else {
			hot = 'N';
		}
		message.setIsHot(hot);
		message.setUpdateId(updateId);
		message.setUpdateDate(current);
		messageDao.update(message);
	}

	@Override
	public Message getMsg(Integer msgNo) {
		return messageDao.getMsg(msgNo);
	}

	@Override
	public void saveMessage(Message message) {
		messageDao.saveOrUpdate(message);
	}
	
}
