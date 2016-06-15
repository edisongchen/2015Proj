package com.proj.common.persistence;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BaseEntity<T> implements Serializable {

	private static final long	serialVersionUID	= 1L;

	protected String			remarks;
	protected String			delFlag;
	protected Date				createDate;
	protected Date				updateDate;

	protected Page<T> page;
	
	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String pRemarks) {
		remarks = pRemarks;
	}



	public String getDelFlag() {
		return delFlag;
	}



	public void setDelFlag(String pDelFlag) {
		delFlag = pDelFlag;
	}


	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date pCreateDate) {
		createDate = pCreateDate;
	}


	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getUpdateDate() {
		return updateDate;
	}



	public void setUpdateDate(Date pUpdateDate) {
		updateDate = pUpdateDate;
	}
	
	

	public Page<T> getPage() {
		if (page == null) {
			page = new Page<T>();
		}
		return page;
	}



	public Page<T> setPage(Page<T> page) {
		this.page=page;
		return page;
	}



	// 删除标记(0正常，1删除，2审核)
	public static final String	DEL_FLAG_NORMAL	= "0";
	public static final String	DEL_FLAG_DELETE	= "1";
	public static final String	DEL_FLAG_AUDIT	= "2";
}
