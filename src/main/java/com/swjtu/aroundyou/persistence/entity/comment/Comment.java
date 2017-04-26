package com.swjtu.aroundyou.persistence.entity.comment;

import java.io.Serializable;
import java.util.Date;

import com.swjtu.aroundyou.persistence.entity.message.Message;
import com.swjtu.aroundyou.persistence.entity.user.UserInfo;
import com.swjtu.aroundyou.utils.DateUtil;
import com.swjtu.aroundyou.utils.FormatUtil;

public class Comment implements Serializable{

	private Integer cmtNo;
	private String cmtContent;
	private Comment comment;
	private Integer messageNo;
	private UserInfo userInfo;
	
	private Integer entryId;
	private Date entryDate;
	private Integer deleteId;
	private Date deleteDate;
	private String createDateString;
	
	public Integer getCmtNo() {
		return cmtNo;
	}
	public void setCmtNo(Integer cmtNo) {
		this.cmtNo = cmtNo;
	}
	public String getCmtContent() {
		return cmtContent;
	}
	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public Integer getMessageNo() {
		return messageNo;
	}
	public void setMessageNo(Integer messageNo) {
		this.messageNo = messageNo;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public Integer getEntryId() {
		return entryId;
	}
	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Integer getDeleteId() {
		return deleteId;
	}
	public void setDeleteId(Integer deleteId) {
		this.deleteId = deleteId;
	}
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	@SuppressWarnings("deprecation")
	public String getCreateDateString() {
		Date current = new Date();
		
		int diff = DateUtil.dateDiff(current, entryDate);
		if (diff < 60 && diff > 1) {
			return diff + "分钟前";
		}else if (diff > 59 && diff < 721) {
			return diff/60 + "小时前";
		}else if (diff < 1) {
			return "刚刚";
		}else {
			if (current.getYear() != entryDate.getYear()) {
				return FormatUtil.formatDate(entryDate, "yyyy-MM-dd  HH:mm");
			}else {
				return  FormatUtil.formatDate(entryDate, "MM-dd  HH:mm");
			}
		}
	}
}
