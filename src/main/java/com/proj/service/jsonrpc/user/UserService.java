/**
 * 
 */
package com.proj.service.jsonrpc.user;

import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.proj.entity.jsonrpc.JsonRpc;

/**
 * 
 * @author ctg
 * @date 2016年2月27日
 */
@JsonRpcService("UserService")
public interface UserService {

	@JsonRpcErrors(
	    @JsonRpcError(exception = Exception.class,message ="test my exception",code=-187))
	String getUserById(JsonRpc name) throws Exception;
}
