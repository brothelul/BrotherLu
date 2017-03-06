package com.swjtu.aroundyou.persistence.entity.user;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author brotherlu
 * @date 下午12:35:28
 * <p>描述：the entity of user login info</p>
 */
public class UserLogin implements Serializable{

	private static final long serialVersionUID = -104124417338418994L;
	
	private UserId id;
	private String username;
	private String password;

	private Integer createId;
	private Date createDate;
	
	public UserId getId() {
		return id;
	}
	public void setId(UserId id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getCreateId() {
		return createId;
	}
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
