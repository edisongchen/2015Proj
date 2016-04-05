package com.proj.entity.jqgrid;

import java.util.List;

public class JsonReader {

	private String page;
	private Integer total;
	private String records;
	private List<Row> rows;
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getRecords() {
		return records;
	}
	public void setRecords(String records) {
		this.records = records;
	}
	public List<Row> getRows() {
		return rows;
	}
	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
	
}
