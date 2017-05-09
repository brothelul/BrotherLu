package com.swjtu.aroundyou.web.controller.utils;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.swjtu.aroundyou.biz.service.user.UserService;
import com.swjtu.aroundyou.persistence.entity.user.UserInfo;
import com.swjtu.aroundyou.persistence.entity.utils.JsonResponseObject;

@Controller
public class UploadFileUtil {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="user/fileUpload.do",method=RequestMethod.POST)
	@ResponseBody
	public String uploadFile(@RequestParam("file")MultipartFile file,
			HttpServletRequest request) throws IllegalStateException, IOException{
		JsonResponseObject responseObject = new JsonResponseObject();

		if(file!=null && file.getSize()>0){//有图            
			String originalFilename = file.getOriginalFilename();	            
			if( !(originalFilename.endsWith(".jpg") || originalFilename.endsWith(".jpeg")
	                  || originalFilename.endsWith(".png") || originalFilename.endsWith(".gif")) ){
	                  throw new RuntimeException("图片格式不正确==>"+originalFilename);	           
			}	            
			String path1 = "E://workspace//AroundYou//src//main//webapp//user//";
			String path = request.getSession().getServletContext().getRealPath("/");
			System.out.println(path);
			path = path +"/user/";
			String GOODS_PIC_DIR ="resources/images/";//商品图片         
			File dir = new File(path+GOODS_PIC_DIR);	            
			if (!dir.exists()) {//目录不存在则创建目录	            
				dir.mkdir();
	       }
	            
	    String newFilename = (long)(Math.random()*1000000)	                        
	    		+originalFilename.substring(originalFilename.lastIndexOf("."));	
	    File newFile = new File(path1+GOODS_PIC_DIR ,newFilename);
	    file.transferTo(newFile);//保存	
	    File file2 = new File(path+GOODS_PIC_DIR,newFilename);
	    FileUtils.copyFile(newFile, file2);
	    file2.createNewFile();
	    newFile.createNewFile();
	   
//	    file.transferTo(new File(path1+GOODS_PIC_DIR ,newFilename));
//	    FileUtils.cop(file, new File(path+GOODS_PIC_DIR ,newFilename));
	    responseObject.setStatus("200");		 
	    responseObject.setResult(GOODS_PIC_DIR +newFilename);
		return JSON.toJSONString(responseObject);
	  }else {
		  responseObject.setStatus("500");		
		  return JSON.toJSONString(responseObject);
	}
	}
	
	@RequestMapping(value="user/uploadUserId.do",method=RequestMethod.POST)
	@ResponseBody
	public String uploadUserId(@RequestParam("file")MultipartFile file,
			HttpServletRequest request) throws IllegalStateException, IOException{
		JsonResponseObject responseObject = new JsonResponseObject();

		if(file!=null && file.getSize()>0){//有图            
			String originalFilename = file.getOriginalFilename();	            
			if( !(originalFilename.endsWith(".jpg") || originalFilename.endsWith(".jpeg")
	                  || originalFilename.endsWith(".png") || originalFilename.endsWith(".gif")) ){
	                  throw new RuntimeException("图片格式不正确==>"+originalFilename);	           
			}	            
			String path1 = "E://workspace//AroundYou//src//main//webapp//user//";
			String path = request.getSession().getServletContext().getRealPath("/");
			System.out.println(path);
			path = path +"/user/";
			String GOODS_PIC_DIR ="resources/images/userId/";//商品图片         
			File dir = new File(path+GOODS_PIC_DIR);	            
			if (!dir.exists()) {//目录不存在则创建目录	            
				dir.mkdir();
	       }
	            
	    String newFilename = (long)(Math.random()*1000000)	                        
	    		+originalFilename.substring(originalFilename.lastIndexOf("."));	
	    File newFile = new File(path1+GOODS_PIC_DIR ,newFilename);
	    file.transferTo(newFile);//保存	
	    File file2 = new File(path+GOODS_PIC_DIR,newFilename);
	    FileUtils.copyFile(newFile, file2);
	    file2.createNewFile();
	    newFile.createNewFile();
	    
	    UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
	    userInfo = userService.getUserInfo(userInfo.getUserNo());
	    userInfo.setIdentifyUri(GOODS_PIC_DIR+newFilename);
	    userInfo.setPassIdentify(1);
	    userService.saveUserInfo(userInfo);
	    
	    request.getSession().setAttribute("userInfo", userInfo);
	    responseObject.setStatus("200");		 
		return JSON.toJSONString(responseObject);
	  }else {
		  responseObject.setStatus("500");		
		  return JSON.toJSONString(responseObject);
	}
	}
}
