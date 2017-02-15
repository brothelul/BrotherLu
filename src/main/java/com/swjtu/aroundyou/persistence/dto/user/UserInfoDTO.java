package com.swjtu.aroundyou.persistence.dto.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author brotherlu
 * @date 下午12:35:59
 * <p>描述：user base info</p>
 */
public class UserInfoDTO implements Serializable{

	private static final long serialVersionUID = 6014071030748333040L;

	private UserIdDTO id;
	private String username;
	private String faviconUri;
	private String shortDesc;
	private String longDesc;
	private String emailAddress;
	private String identifyUri;  //存放认证的图片
	private Integer passIdentify;  //是否通过认证，1 为通过 ， 0 为未通过，初始值为3
	private Integer friend;  //存放友好度，用于限制恶劣用户评论和发表反动信息， 总值为5，扣分由后台操作
	private String lastLoginIP;
	private Date lastLoginDate;
	
	public UserIdDTO getId() {
		return id;
	}
	public void setId(UserIdDTO id) {
		this.id = id;
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
