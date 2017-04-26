package com.swjtu.aroundyou.persistence.entity.manager;

import java.io.Serializable;

public class ManagerInfo implements Serializable{

	private Integer managerNo;
	private String managerName;
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
}
