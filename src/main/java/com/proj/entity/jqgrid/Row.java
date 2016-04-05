package com.proj.entity.jqgrid;

import java.util.List;

public class Row {
	private String id;
	private List<Object> cell;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Object> getCell() {
		return cell;
	}
	public void setCell(List<Object> cell) {
		this.cell = cell;
	}
	
}
