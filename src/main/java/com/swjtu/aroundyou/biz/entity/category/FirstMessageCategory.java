package com.swjtu.aroundyou.biz.entity.category;

import java.util.Date;
import java.util.List;

import com.swjtu.aroundyou.persistence.dto.category.SecondMessageCategoryDTO;

public class FirstMessageCategory {

	private Integer categoryNo;
	private String categoryName;
	private Integer createId;
	private Date createDate;
	private Integer updateId;
	private Date updateDate;
	private Integer deleteId;
	private Date deleteDate;
	private List<SecondMessageCategory> secondCategoryList;
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
	public List<SecondMessageCategory> getSecondCategoryList() {
		return secondCategoryList;
	}
	public void setSecondCategoryList(List<SecondMessageCategory> secondCategoryList) {
		this.secondCategoryList = secondCategoryList;
	}	
}
