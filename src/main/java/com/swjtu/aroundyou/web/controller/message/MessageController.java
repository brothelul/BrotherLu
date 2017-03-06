package com.swjtu.aroundyou.web.controller.message;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.swjtu.aroundyou.biz.service.message.MessageService;
import com.swjtu.aroundyou.persistence.entity.message.Message;

@Controller
public class MessageController {

	private Logger logger = Logger.getLogger(MessageController.class);
	
	@Autowired
	private MessageService messageService;	
	
	@RequestMapping(value="getHotMessages.do")
	@ResponseBody
	public String getHotMessages(){
		
		List<Message> sliderMessages = messageService.findHotMessages();
	    String hotMessageJson = "";
	    
	    try {
	    	hotMessageJson = JSON.toJSONString(sliderMessages);
		} catch (Exception e) {
			
			logger.warn("get slider messages json failed");
		}
		
	    return hotMessageJson;
	}
	
	@RequestMapping(value="getAllMessages.do")
	@ResponseBody
	public String getAllMessages(){
		
		List<Message> sliderMessages = messageService.findAllMessages();
	    String messagesJson = "";
	    
	    try {
	    	messagesJson = JSON.toJSONString(sliderMessages);
		} catch (Exception e) {
			
			logger.warn("get messages json failed");
		}
		
	    return messagesJson;
	}
	
	@RequestMapping(value="getTodayMostHot.do")
	@ResponseBody
	public String getTodayMostHot(){
		
		List<Message> sliderMessages = messageService.findTodayMostHot();
	    String TodayHotJson = "";
	    
	    try {
	    	TodayHotJson = JSON.toJSONString(sliderMessages);
		} catch (Exception e) {
			
			logger.warn("get today most messages json failed");
		}
		
	    return TodayHotJson;
	}	
}
