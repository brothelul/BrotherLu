package com.swjtu.aroundyou.test.utils;

import org.testng.annotations.Test;

import com.swjtu.aroundyou.utils.MailUitl;

public class TestMail {

	@Test
	public void testMail(){
		MailUitl.sendMail("oracle@shop.com", "1");
	}
}
