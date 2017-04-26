package com.swjtu.aroundyou.persistence.entity.manager;

import java.io.Serializable;
import java.util.Date;

import com.swjtu.aroundyou.utils.DateUtil;
import com.swjtu.aroundyou.utils.FormatUtil;

public class ManagerLoginLog implements Serializable{

	private Integer logNo;
	private Integer managerNo;
	private String loginIP;
	private Date loginDate;
	private String loginDateString;
	public Integer getLogNo() {
		return logNo;
	}
	public void setLogNo(Integer logNo) {
		this.logNo = logNo;
	}
	public Integer getManagerNo() {
		return managerNo;
	}
	public void setManagerNo(Integer managerNo) {
		this.managerNo = managerNo;
	}
	public String getLoginIP() {
		return loginIP;
	}
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getLoginDateString(){
		return FormatUtil.formatDate(loginDate, "yyyy-MM-dd HH:mm:ss");
	}
}
