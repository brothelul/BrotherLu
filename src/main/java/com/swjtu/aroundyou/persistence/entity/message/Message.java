package com.swjtu.aroundyou.persistence.entity.message;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.swjtu.aroundyou.persistence.entity.category.SecondMessageCategory;
import com.swjtu.aroundyou.persistence.entity.comment.Comment;
import com.swjtu.aroundyou.persistence.entity.user.UserInfo;
import com.swjtu.aroundyou.utils.DateUtil;
import com.swjtu.aroundyou.utils.FormatUtil;

public class Message implements Serializable {

	private static final long serialVersionUID = -6395741046180688819L;

	private Integer messageNo;
	private String messageName;
	private String messageTitle;
	private String photoUri;
	private Integer active;
	private String messageContent;
	private Character isHot;
	private Integer viewCount;
	private String createDateString;
	private SecondMessageCategory secondMessageCategory;
	private UserInfo userInfo;
	private List<Comment> comments;
	private Integer cmtCount;
	
	private Integer createId;
	private Date createDate;
	private Integer updateId;
	private Date updateDate;
	private Integer deleteId;
	private Date deleteDate;
	
	public Integer getMessageNo() {
		return messageNo;
	}
	public void setMessageNo(Integer messageNo) {
		this.messageNo = messageNo;
	}
	public String getMessageName() {
		return messageName;
	}
	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}
	public String getMessageTitle() {
		return messageTitle;
	}
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	public String getPhotoUri() {
		return photoUri;
	}
	public void setPhotoUri(String photoUri) {
		this.photoUri = photoUri;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public Character getIsHot() {
		return isHot;
	}
	public void setIsHot(Character isHot) {
		this.isHot = isHot;
	}
	public Integer getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	@SuppressWarnings("deprecation")
	public String getCreateDateString() {
		Date current = new Date();
		int diff = DateUtil.dateDiff(current, createDate);
		if (diff < 60 && diff > 1) {
			return diff + "分钟前";
		}else if (diff > 59 && diff < 721) {
			return diff/60 + "小时前";
		}else if (diff < 1) {
			return "刚刚";
		}else {
			if (current.getYear() != createDate.getYear()) {
				return  FormatUtil.formatDate(createDate, "yyyy-MM-dd  HH:mm");
			}else {
				return  FormatUtil.formatDate(createDate, "MM-dd  HH:mm");
			}
		}
	}
	public Integer getCmtCount() {
		return cmtCount;
	}
	public void setCmtCount(Integer cmtCount) {
		this.cmtCount = cmtCount;
	}
	public SecondMessageCategory getSecondMessageCategory() {
		return secondMessageCategory;
	}
	public void setSecondMessageCategory(
			SecondMessageCategory secondMessageCategory) {
		this.secondMessageCategory = secondMessageCategory;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
	public Integer getUpdateId() {
		return updateId;
	}
	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
	
}
