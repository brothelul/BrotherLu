package com.swjtu.aroundyou.persistence.entity.manager;

import java.io.Serializable;

public class ManagerLogin implements Serializable{
	private Integer managerNo;
	private String managerName;
	private String password;
	public Integer getManagerNo() {
		return managerNo;
	}
	public void setManagerNo(Integer managerNo) {
		this.managerNo = managerNo;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
}
