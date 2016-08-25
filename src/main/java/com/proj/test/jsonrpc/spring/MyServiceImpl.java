/**
 * 
 */
package com.proj.test.jsonrpc.spring;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import com.google.common.collect.Maps;
import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.proj.common.mapper.JsonMapper;
import com.proj.entity.jsonrpc.User;

/**
 * 
 * @author ctg
 * @date 2016年2月25日
 */
@Component
public class MyServiceImpl implements MyService{

	static JsonMapper mapper = JsonMapper.getInstance();
	@Override
	public String getUser() {
		System.out.println("getUser//////////////");
		Map<String, Object> ret = Maps.newHashMap();
    	ret.put("id", "2");
    	ret.put("result", "success1");
    	ret.put("jsonrpc","1.2");
    	return mapper.toJson(ret);
	}
	
	@Override
	public String getUser(String name) {
		Map<String, Object> ret = Maps.newHashMap();
    	ret.put("id", "1");
    	ret.put("result", UUID.randomUUID().toString());
    	ret.put("jsonrpc","2.0");
    	System.out.println("MyServiceImpl:"+JsonMapper.getInstance().toJson(ret));
		return mapper.toJson(ret);
	}

	@Override
	public User getUserEntity() {
		System.out.println("getUserEntity");
		
		return new User();
	}

	@Override
	public User getUserEntity2(String name) {
		System.out.println("getUserEntity2:"+name);
		return new User(name, name, null);
	}

	@Override
	@JsonRpcErrors(@JsonRpcError(exception = Exception.class,message ="a throws exception",code=-187))
	public User createUser(String name, String password) throws Exception {
		System.out.println("createUser:"+name+" pwd:"+password);
		if (true) {
			throw new Exception("dddd");
		}
		return new User(name, name, password);
	}

	@Override
	public String testListParam(List<String> properties) {
		return mapper.toJson(properties);
	}

	@Override
	public String testBeanParam(User user) {
		System.out.println(user);
		return mapper.toJson(user);
	}

	@Override
	public String testMap(Map<String, String> map) {
		User user = null;
		System.out.println(user.getFirstName());
		return mapper.toJson(map);
	}

}
