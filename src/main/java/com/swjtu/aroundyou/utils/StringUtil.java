package com.swjtu.aroundyou.utils;

public class StringUtil {
	private StringUtil(){}
	public static String getSearchKey(String keyWord){
		String[] keys = keyWord.split("");
		String key = "";
		for (int i = 1; i < keys.length; i++) {
			key = "%"+keys[i]+"%";
		}
		return key;
	}
}
