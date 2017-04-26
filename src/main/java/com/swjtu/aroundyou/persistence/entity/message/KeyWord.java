package com.swjtu.aroundyou.persistence.entity.message;

import java.io.Serializable;
import java.util.Date;

import com.swjtu.aroundyou.utils.FormatUtil;

public class KeyWord implements Serializable{

	private Integer keyNo;
	private String keyWord;
	private String IP;
	private Date searchDate;
	private String searchDateString;
	public Integer getKeyNo() {
		return keyNo;
	}
	public void setKeyNo(Integer keyNo) {
		this.keyNo = keyNo;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public Date getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}
	public String getSearchDateString() {
		return FormatUtil.formatDate(searchDate, "yyyy-MM-dd HH:mm:ss");
	}	
}
