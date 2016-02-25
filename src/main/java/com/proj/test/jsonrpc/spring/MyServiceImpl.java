/**
 * 
 */
package com.proj.test.jsonrpc.spring;

import java.util.Map;

import com.google.common.collect.Maps;
import com.proj.common.mapper.JsonMapper;
import com.proj.test.jsonrpc.User;

/**
 * 
 * @author ctg
 * @date 2016年2月25日
 */
public class MyServiceImpl implements MyService{

	public String getUser() {
		System.out.println("//////////////");
		Map<String, Object> ret = Maps.newHashMap();
    	ret.put("id", "1");
    	ret.put("result", "success");
    	ret.put("jsonrpc","1.1");
    	//JsonMapper.getInstance().toJson(ret)
		return "success";
	}
	@Override
	public String getUser2(String name) {
		Map<String, Object> ret = Maps.newHashMap();
    	ret.put("id", "1");
    	ret.put("result", "success");
    	ret.put("jsonrpc","1.1");
    	System.out.println(JsonMapper.getInstance().toJson(ret));
		return JsonMapper.getInstance().toJson(ret);
	}

}
