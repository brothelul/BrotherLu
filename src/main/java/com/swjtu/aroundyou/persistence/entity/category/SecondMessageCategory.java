package com.swjtu.aroundyou.persistence.entity.category;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.swjtu.aroundyou.persistence.entity.manager.ManagerInfo;
import com.swjtu.aroundyou.persistence.entity.message.Message;
import com.swjtu.aroundyou.utils.FormatUtil;


/**
 * 
 * @author brotherlu
 * @date 下午8:55:32
 * <p>描述：second message category</p>
 */
public class SecondMessageCategory implements Serializable{

	private static final long serialVersionUID = 3033559410286115201L;

	private Integer categoryNo;
	private String categoryName;
	private String categoryDesc;
	private FirstMessageCategory firstCategoryDTO;
	private List<Message> messageDTOs;
	private ManagerInfo managerInfo;
	
	private Integer createId;
	private Date createDate;
	private String createDateString;
	private Integer updateId;
	private Date updateDate;
	private Integer deleteId;
	private Date deleteDate;

	public Integer getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(Integer categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public FirstMessageCategory getFirstCategoryDTO() {
		return firstCategoryDTO;
	}
	public void setFirstCategoryDTO(FirstMessageCategory firstCategoryDTO) {
		this.firstCategoryDTO = firstCategoryDTO;
	}	
	public List<Message> getMessageDTOs() {
		return messageDTOs;
	}
	public ManagerInfo getManagerInfo() {
		return managerInfo;
	}
	public void setManagerInfo(ManagerInfo managerInfo) {
		this.managerInfo = managerInfo;
	}
	public void setMessageDTOs(List<Message> messageDTOs) {
		this.messageDTOs = messageDTOs;
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
	public String getCategoryDesc() {
		return categoryDesc;
	}
	
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	public String getCreateDateString() {
		return FormatUtil.formatDate(createDate, "yyyy-MM-dd HH:mm:ss");
	}
}
