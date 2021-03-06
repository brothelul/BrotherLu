package com.swjtu.aroundyou.persistence.entity.category;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author brotherlu
 * @date 下午8:55:00
 * <p>描述：first message category </p>
 */
public class FirstMessageCategory implements Serializable{

	private static final long serialVersionUID = 5128883379551492550L;

	private Integer categoryNo;
	private String categoryName;
	private List<SecondMessageCategory> secondCategorys;
	
	private Integer createId;
	private Date createDate;
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
	public List<SecondMessageCategory> getSecondCategorys() {
		return secondCategorys;
	}
	public void setSecondCategorys(List<SecondMessageCategory> secondCategorys) {
		this.secondCategorys = secondCategorys;
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
