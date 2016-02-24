/**
 * 
 */
package com.proj.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 
 * @author ctg
 * @date 2016年2月24日
 */
public class JsonRpc {
	 private String method;

	    private Map<String, Object> params;

	    public String getMethod() {
	        return method;
	    }

	    public void setMethod(String method) {
	        this.method = method;
	    }

	    public Map getParams() {
	        return params;
	    }

	    public void setParams(Map params) {
	        this.params = params;
	    }

	    public JsonRpc() {
	        newTag();
	    }

	    public void newTag() {
	        if (params == null) {
	            params = new HashMap();
	        }
	        if(!params.containsKey("tag")) {
	            params.put("tag", UUID.randomUUID().toString());
	            params.put("auth", "-");
	        }
	    }
}
