/**
 * 
 */
package com.proj.test.jsonrpc.spring;

import java.util.List;
import java.util.Map;

import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.proj.test.jsonrpc.User;

/**
 * 
 * @author ctg
 * @date 2016年2月25日
 */
@JsonRpcService("MyService")
public interface MyService {

	@JsonRpcErrors(@JsonRpcError(exception = Exception.class,message ="test exception",code=-187))
	String getUser();
	String getUser(String name);
	User getUserEntity();
	User getUserEntity2(@JsonRpcParam("name2")String name);
	@JsonRpcErrors(@JsonRpcError(exception = Exception.class,message ="a throws exception",code=-187))
	User createUser(@JsonRpcParam("name2")String name,@JsonRpcParam("pwd2")String password) throws Exception;
	String testListParam(@JsonRpcParam("properties") final List<String> properties);
	String testBeanParam(User user);
	String testMap(Map<String, String> map);
}
