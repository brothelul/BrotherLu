package com.swjtu.aroundyou.test;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AbstractTest {

	protected Logger logger = Logger.getLogger(AbstractTest.class);
	
	private ApplicationContext getCtx(){
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");		
		return ctx;
	}
	
	protected Object getBean(String className){
		
		return getCtx().getBean(className);
	}
}
