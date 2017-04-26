package com.swjtu.aroundyou.utils;

import java.util.Date;

public class DateUtil {

	private DateUtil(){}
	
	public static int dateDiff(Date date,Date date2){
		long diff = date.getTime() - date2.getTime();
		long diffM = diff/60000;  //返回分钟
		return (int) diffM;
	}
}
