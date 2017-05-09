package com.swjtu.aroundyou.web.controller.message;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import test.factory.FactoryAndTestMethodTest.NullArgsTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.swjtu.aroundyou.biz.service.category.CategoryService;
import com.swjtu.aroundyou.biz.service.comment.CommentService;
import com.swjtu.aroundyou.biz.service.message.KeyWordService;
import com.swjtu.aroundyou.biz.service.message.MessageService;
import com.swjtu.aroundyou.biz.service.user.UserService;
import com.swjtu.aroundyou.biz.service.utils.AppConfigService;
import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.entity.category.SecondMessageCategory;
import com.swjtu.aroundyou.persistence.entity.comment.Comment;
import com.swjtu.aroundyou.persistence.entity.manager.ManagerInfo;
import com.swjtu.aroundyou.persistence.entity.message.HostKey;
import com.swjtu.aroundyou.persistence.entity.message.KeyWord;
import com.swjtu.aroundyou.persistence.entity.message.Message;
import com.swjtu.aroundyou.persistence.entity.user.UserInfo;
import com.swjtu.aroundyou.persistence.entity.utils.AppConfig;
import com.swjtu.aroundyou.persistence.entity.utils.JsonResponseObject;
import com.swjtu.aroundyou.utils.MailUitl;
import com.swjtu.aroundyou.utils.SimplePropertyFilter;

@Controller
public class MessageController {

	private Logger logger = Logger.getLogger(MessageController.class);
	private final String SPT = "SPT";
	
	@Autowired
	private MessageService messageService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	@Autowired
	private AppConfigService appConfigService;
	@Autowired
	private KeyWordService keyWordService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="user/getHotMessages.do")
	@ResponseBody
	public String getHotMessages(){
		
		List<Message> sliderMessages = messageService.findHotMessages();		
	    return JSON.toJSONString(sliderMessages);
	}
	
	@RequestMapping(value="user/getAllMessages.do",method=RequestMethod.POST)
	@ResponseBody
	public String getAllMessages(@RequestParam("page")Integer page,
			         @RequestParam("pageSize")Integer pageSize){
		JsonResponseObject responseObject = new JsonResponseObject();
		Pagination<Message> messages = messageService.findAllMessages(page,pageSize);
		responseObject.setStatus("200");
		responseObject.setResult(messages);	    
	    return JSON.toJSONString(responseObject,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@RequestMapping(value="user/getTodayMostHot.do")
	@ResponseBody
	public String getTodayMostHot(){
		
		List<Message> sliderMessages = messageService.findTodayMostHot();	
	    return JSON.toJSONString(sliderMessages);
	}
	
	@RequestMapping(value="user/getMessage.do",method= RequestMethod.GET)
	public String getMessage(@RequestParam("messageNo")Integer messageNo,HttpSession session){
		Message message = messageService.getMessage(messageNo);
		session.setAttribute("message", message); 

		return "user/message_detail";
	}
	
	@RequestMapping(value="user/loadComments.do",method=RequestMethod.POST)
	@ResponseBody
	public String loadComments(HttpSession session,
			@RequestParam("page")Integer page,
			@RequestParam("pageSize")Integer pageSize){

		Message message = (Message) session.getAttribute("message");	
		JsonResponseObject responseObject = new JsonResponseObject();
		Integer messageNo = message.getMessageNo();
		Pagination<Comment> comments = commentService.getCommentByMessageNo(messageNo,page,pageSize);

		responseObject.setStatus("200");
		responseObject.setResult(comments);
		
		return JSON.toJSONString(responseObject,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@RequestMapping(value="user/saveComment.do",method=RequestMethod.POST)
	@ResponseBody
	public String saveComment(@RequestParam("cmtContent")String cmtContent,
			HttpSession session){
		JsonResponseObject responseObject = new JsonResponseObject();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Message message = (Message) session.getAttribute("message");
		Date current = new Date();
		
		if (cmtContent == null || "".equals(cmtContent)) {
			responseObject.setResult("评论不能为空");
			responseObject.setStatus("500");
			return JSON.toJSONString(responseObject);
		}
		if (userInfo == null) {
			responseObject.setResult("未登录");
			responseObject.setStatus("401");
			return JSON.toJSONString(responseObject);
		}

		Comment comment = new Comment();
		comment.setCmtContent(cmtContent);
		comment.setUserInfo(userInfo);
		comment.setMessageNo(message.getMessageNo());
		comment.setEntryId(userInfo.getUserNo());
		comment.setEntryDate(current);
		commentService.createComment(comment);
		
		Integer cmtCount = message.getCmtCount() + 1;
		messageService.updateCmtCount(cmtCount,message.getMessageNo());
		responseObject.setStatus("200");

		return JSON.toJSONString(responseObject);
	}
	
	@RequestMapping(value = "user/loadCatMessages.do",method= RequestMethod.POST)
	@ResponseBody
	public String loadCatMessages(@RequestParam("page")Integer page,
	         @RequestParam("pageSize")Integer pageSize,
	         @RequestParam("cateNo")Integer cateNo){
		
		Pagination<Message> messages = messageService.getMessagesByCat(cateNo,page,pageSize);
		JsonResponseObject jsonResponseObject = new JsonResponseObject();
		jsonResponseObject.setStatus("200");
		jsonResponseObject.setResult(messages);
		
		return JSON.toJSONString(jsonResponseObject,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@RequestMapping(value="user/specialTopic.do",method=RequestMethod.GET)
	@ResponseBody
	public String  getSpecialTopic(){
		JsonResponseObject responseObject = new JsonResponseObject();
		AppConfig appConfig = appConfigService.getAppConfig(SPT);
		responseObject.setStatus("200");
		responseObject.setResult(appConfig);
		return JSON.toJSONString(responseObject);
	}	
	
	@RequestMapping(value="user/loadSearchMessages.do",method=RequestMethod.POST)
	@ResponseBody
	public String loadSearchMessages(@RequestParam("page")Integer page,
			@RequestParam("pageSize")Integer pageSize,
			@RequestParam("key")String key,
			HttpServletRequest request) throws UnsupportedEncodingException {
		key = URLDecoder.decode(key, "utf-8");
		JsonResponseObject responseObject = new JsonResponseObject();
		String ip = request.getRemoteAddr();
		Date current = new Date();
		if (key == null || key.equals("")) {
			responseObject.setStatus("400");
			return JSON.toJSONString(responseObject);
		}
		
		KeyWord keyWord = new KeyWord();
		keyWord.setIP(ip);
		keyWord.setKeyWord(key);
		keyWord.setSearchDate(current);
		keyWordService.saveKeyWord(keyWord);
		
		Pagination<Message> messages = messageService.searchMessages(key, page,pageSize);
		responseObject.setStatus("200");
		responseObject.setResult(messages);
		return JSON.toJSONString(responseObject,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@RequestMapping(value="user/loadHostKey.do",method=RequestMethod.GET)
	@ResponseBody
	public String loadHostKey(){
		JsonResponseObject responseObject = new JsonResponseObject();
		List<String> hostKeys = keyWordService.getHostKey();
		if (hostKeys != null && hostKeys.size() > 0) {
			responseObject.setStatus("200");
			responseObject.setResult(hostKeys);
		}
		return JSON.toJSONString(responseObject);
	}

	@RequestMapping(value="user/toUserPage.do",method=RequestMethod.GET)
	public String toUserPage(@RequestParam("userNo")Integer userNo,
			HttpSession session){
		UserInfo author = userService.getUserInfo(userNo);
		session.setAttribute("author", author);
		return "user/user_index";
	}
	
	@RequestMapping(value="user/loadAuthorMessages.do",method=RequestMethod.POST)
	@ResponseBody
	public String loadAuthorMessages(HttpSession session,
			@RequestParam("page") Integer page,
			@RequestParam("pageSize")Integer pageSize,
			@RequestParam("type")Integer type){
		JsonResponseObject responseObject = new JsonResponseObject();
		UserInfo userInfo = null;
		if (type==2) {
			userInfo = (UserInfo) session.getAttribute("author");

		}else {
			userInfo = (UserInfo) session.getAttribute("userInfo");
		}

		if (userInfo == null) {
			responseObject.setStatus("500");
			return JSON.toJSONString(responseObject);
		}
		
		Pagination<Message> messages = messageService.getMessagesByUser(userInfo.getUserNo(), page,pageSize,type);
		responseObject.setStatus("200");
		responseObject.setResult(messages);
		return JSON.toJSONString(responseObject,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@RequestMapping(value="manager/loadAllMessgae.do",method=RequestMethod.POST)
	@ResponseBody
	public String loadAllMessgae(@RequestParam("page")Integer page,
			@RequestParam("pageSize")Integer pageSize){
		JsonResponseObject responseObject = new JsonResponseObject();
		Pagination<Message> messages = messageService.getMessgaesForManage(page, pageSize);
		responseObject.setStatus("200");
		responseObject.setResult(messages);
		return JSON.toJSONString(responseObject,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@RequestMapping(value="manager/loadAllComments.do",method=RequestMethod.POST)
	@ResponseBody
	public String loadAllComments(@RequestParam("page")Integer page,
			@RequestParam("pageSize")Integer pageSize){
		JsonResponseObject responseObject = new JsonResponseObject();
		Pagination<Comment> comments = commentService.getAllComments(page, pageSize);
		responseObject.setStatus("200");
		responseObject.setResult(comments);
		return JSON.toJSONString(responseObject,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@RequestMapping(value= "manager/deleteMsg.do",method=RequestMethod.GET)
	public String deleteMessgae(@RequestParam("msgNo")Integer msgNo,
			HttpSession session){
		Date current = new Date();
		ManagerInfo managerInfo = (ManagerInfo) session.getAttribute("managerInfo");
		if (managerInfo == null) {
			return "redirect:error.html";
		}
		Integer deleteId = managerInfo.getManagerNo();
		messageService.deleteMsg(msgNo, deleteId, current);
		return "redirect:imgtable.html";
	}
	
	@RequestMapping(value="manager/deleteComment.do",method=RequestMethod.GET)
	public String deleteComment(@RequestParam("cmtNo")Integer cmtNo,HttpSession session){
		Date current = new Date();
		ManagerInfo managerInfo = (ManagerInfo) session.getAttribute("managerInfo");
		if (managerInfo == null) {
			return "redirect:error.html";
		}
		Integer deleteId = managerInfo.getManagerNo();
		commentService.deleteComment(cmtNo,deleteId,current);
		Comment comment = commentService.getComment(cmtNo);
		Message message = messageService.getMessage(comment.getMessageNo());
		messageService.updateCmtCount(message.getCmtCount()-1 , message.getMessageNo());
		return "redirect:comments.html";
	}
	
	@RequestMapping(value="manager/active.do",method=RequestMethod.GET)
	public String activeMessage(@RequestParam("type")Integer type,
			@RequestParam("msgNo")Integer msgNo,
			HttpSession session){
		Date current = new Date();
		ManagerInfo managerInfo = (ManagerInfo) session.getAttribute("managerInfo");
		if (managerInfo == null) {
			return "redirect:error.html";
		}
		Integer updateId = managerInfo.getManagerNo();
		messageService.activeMessage(type,updateId,current,msgNo);
		Message message = messageService.getMsg(msgNo);
		if (type == 0) {
			String content = "你的消息（编号："+msgNo+"）未通过审核，请修改后再次提交。";
			MailUitl.sendMail(message.getUserInfo().getEmailAddress(), content);
		}
		if (type == 1) {
			String content = "你的消息（编号："+msgNo+"）通过审核，请到AroundYou查看。";
			MailUitl.sendMail(message.getUserInfo().getEmailAddress(), content);
		}
		return "redirect:imgtable.html";
	}
	
	@RequestMapping(value="manager/getSearch.do",method=RequestMethod.POST)
	@ResponseBody
	public String getKeyWord(@RequestParam("page")Integer page,
			@RequestParam("pageSize")Integer pageSize){
		JsonResponseObject responseObject = new JsonResponseObject();
		Pagination<KeyWord> keyWords = keyWordService.getAllKeyWords(page,pageSize);
		responseObject.setStatus("200");
		responseObject.setResult(keyWords);
		return JSON.toJSONString(responseObject);
	}
	
	@RequestMapping(value="manager/changeHot.do",method=RequestMethod.GET)
	public String changeHot(@RequestParam("msgNo")Integer msgNo,
			HttpSession session){
		ManagerInfo managerInfo = (ManagerInfo) session.getAttribute("managerInfo");
		Date current = new Date();
		if (managerInfo == null) {
			return "redirect:error.html";
		}
		Integer updateId = managerInfo.getManagerNo();
		messageService.changeHot(msgNo,updateId,current);
		return "redirect:imgtable.html";
	}
	
	@RequestMapping(value="user/publishMessage.do")
	public String publishMessage(@RequestParam("msgNo")Integer msgNo,
			HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Date current = new Date();
		if (userInfo == null || userInfo.getUserType() == 0) {
			return "redirect:index.jsp";
		}
		Integer updateId = userInfo.getUserNo();
		messageService.activeMessage(2, updateId, current, msgNo);
		return "redirect:createMessage.jsp";
	}
	
	@RequestMapping(value= "user/deleteMsg.do",method=RequestMethod.GET)
	public String deleteMessgaeU(@RequestParam("msgNo")Integer msgNo,
			HttpSession session){
		Date current = new Date();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		if (userInfo == null || userInfo.getUserType() == 0) {
			return "redirect:index.jsp";
		}
		Integer deleteId = userInfo.getUserNo();
		messageService.deleteMsg(msgNo, deleteId, current);
		return "redirect:createMessage.jsp";
	}
	
	@RequestMapping(value="user/getMsg.do",method=RequestMethod.POST)
	@ResponseBody
	public String getMessage(@RequestParam("msgNo")Integer msgNo){
		JsonResponseObject responseObject = new JsonResponseObject();
		Message message = messageService.getMsg(msgNo);
		responseObject.setStatus("200");
		responseObject.setResult(message);
		return JSON.toJSONString(responseObject);
	}
	
	@RequestMapping(value="user/saveMessage.do",method=RequestMethod.POST)
	@ResponseBody
	public String saveMessage(@RequestParam(value="msgNo",required=false)Integer msgNo,
			@RequestParam("title")String title,
			@RequestParam("name")String name,
			@RequestParam("content")String content,
			@RequestParam("cateNo")Integer cateNo,
			@RequestParam("msgUrl")String msgUrl,
			@RequestParam("type")Integer type,
			HttpSession session){
		JsonResponseObject responseObject = new JsonResponseObject();
		Date current = new Date();
		Message message;
		SecondMessageCategory category = categoryService.getCategory(cateNo);
		if (msgNo == null) {
			message = new Message();
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			if (userInfo == null) {
				responseObject.setStatus("401");
			}		
			message.setCreateId(userInfo.getUserNo());
			message.setCreateDate(current);
			message.setUserInfo(userInfo);
			message.setViewCount(0);
			message.setCmtCount(0);
		}else {
			message = messageService.getMsg(msgNo);
		}
		
		if (type == 0) {
			message.setActive(0);
		}else{
			message.setActive(1);
		}
		message.setIsHot('N');
		message.setMessageContent(content);
		message.setMessageName(name);
		message.setMessageTitle(title);
		message.setSecondMessageCategory(category);
		message.setPhotoUri(msgUrl);
		messageService.saveMessage(message);
		
		responseObject.setStatus("200");		
		return JSON.toJSONString(responseObject);
	}
}
