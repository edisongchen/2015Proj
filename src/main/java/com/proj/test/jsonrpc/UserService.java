/**
 * 
 */
package com.proj.test.jsonrpc;

import com.googlecode.jsonrpc4j.JsonRpcService;

/**
 * 
 * @author ctg
 * @date 2016年2月25日
 */
//@JsonRpcService("/MyService")
public interface UserService {
	User createUser(String userName, String firstName, String password);
	String createUser();
    User findUserByUserName(String userName);
    int getUserCount();
}
