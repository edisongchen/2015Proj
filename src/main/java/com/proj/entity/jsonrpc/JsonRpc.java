/**
 * 
 */
package com.proj.entity.jsonrpc;

import java.util.Map;

/**
 * 
 * @author ctg
 * @date 2016年2月27日
 */
public class JsonRpc {
	private String id;
	private String method;
	private Map<String, Object> params;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}
