/**
 * 
 */
package com.proj.test.jsonrpc.spring;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.proj.test.jsonrpc.User;

/**
 * 
 * @author ctg
 * @date 2016年2月25日
 */
@JsonRpcService("MyService")
public interface MyService {

	String getUser();
	String getUser2(String name);
}
