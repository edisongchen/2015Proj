package com.proj.common.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public abstract class IdEntity<T> extends BaseEntity<T> implements Serializable {
	private static final long	serialVersionUID	= 1L;
	
	protected String id;//编号

	@Id
	@Column(name="id")
	public String getId() {
		return id;
	}

	public void setId(String pId) {
		id = pId;
	}
    
	


}
