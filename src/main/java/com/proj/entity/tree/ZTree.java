package com.proj.entity.tree;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ZTree {

	private String id;
	
	private String name;
	
	@JsonProperty("parent_id")
	private String parent;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	
}
