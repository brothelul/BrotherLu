package com.swjtu.aroundyou.test.utils;

public class TestStringUtil {

	public static void main(String[] args) {
		String string = "中国";
		String[] keys = string.split("");
		for (String string2 : keys) {
			System.out.println(string2+keys.length);
		}
	}
}
