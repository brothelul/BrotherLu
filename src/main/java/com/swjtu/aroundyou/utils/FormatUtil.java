package com.swjtu.aroundyou.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class FormatUtil {

	private static Logger logger = Logger.getLogger(FormatUtil.class);
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年2月25日下午10:36:53
	 * @param String
	 * <p>描述：将日期转化为制定格式的String</p>
	 */
	public static String formatDate(Date date,String pattern){
		
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		
		String dateString = "";
		try {
			
			dateString = format.format(date);
		} catch (Exception e) {
			
			logger.warn("format date failed");
		}
		
		return dateString;
	}
}
