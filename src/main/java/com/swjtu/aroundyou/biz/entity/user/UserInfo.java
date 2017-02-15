package com.swjtu.aroundyou.biz.entity.user;

import java.util.Date;

public class UserInfo {

	private Integer userNo;
	private Integer userType;
	private String username;
	private String faviconUri;
	private String shortDesc;
	private String longDesc;
	private String emailAddress;
	private String identifyUri;  
	private Integer passIdentify;  
	private Integer friend;
	private String lastLoginIP;
	private Date lastLoginDate;
	
	public Integer getUserNo() {
		return userNo;
	}
	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFaviconUri() {
		return faviconUri;
	}
	public void setFaviconUri(String faviconUri) {
		this.faviconUri = faviconUri;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getLongDesc() {
		return longDesc;
	}
	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getIdentifyUri() {
		return identifyUri;
	}
	public void setIdentifyUri(String identifyUri) {
		this.identifyUri = identifyUri;
	}
	public Integer getPassIdentify() {
		return passIdentify;
	}
	public void setPassIdentify(Integer passIdentify) {
		this.passIdentify = passIdentify;
	}
	public Integer getFriend() {
		return friend;
	}
	public void setFriend(Integer friend) {
		this.friend = friend;
	}
	public String getLastLoginIP() {
		return lastLoginIP;
	}
	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
}
