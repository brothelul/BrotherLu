package com.swjtu.aroundyou.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

public class BeanCopyUtil {

	private static Logger logger = Logger.getLogger(BeanUtils.class);
	
	public static void copy(Object source, Object target){
		
		try {		
			BeanUtils.copyProperties(source, target);
		} catch (Exception e) {
			
			logger.warn("copy properties failed,the target Object is null!");
		}
	}
}
