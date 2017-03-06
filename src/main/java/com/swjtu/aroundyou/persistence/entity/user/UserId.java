package com.swjtu.aroundyou.persistence.entity.user;

import java.io.Serializable;

public class UserId implements Serializable{

	private static final long serialVersionUID = -3377038085891752968L;
    
	private Integer userNo;
	private Integer userType;
	
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
	
	public UserId(Integer userNo, Integer userType) {
		this.userNo = userNo;
		this.userType = userType;
	}
	public UserId() {
	}
		
}
