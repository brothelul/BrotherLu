package com.swjtu.aroundyou.test.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


public class test {

	private static Logger logger = Logger.getLogger(test.class);
	  
    public static void main(String[] args) throws IOException {  
        
         String urls = "http://tieba.baidu.com/p/3880588455";
         
         URL url = new URL(urls);
         
         BufferedReader bufferedReader = 
        		 new BufferedReader(new InputStreamReader(url.openStream()));
         
         String line = null;
         String mailReg = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";
         
         Pattern pattern = Pattern.compile(mailReg);
         
         while((line = bufferedReader.readLine()) != null){
        	 
        	 Matcher matcher = pattern.matcher(line);
        	 
        	 while(matcher.find()){
        		 
        		 logger.info(matcher.group());
        	 }
        	 
//        	 logger.info(line);
         }
         
         bufferedReader.close();
    }  
}
