/**
 * 
 */
package com.proj.test.jsonrpc;

import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.Assert;

import org.junit.Test;

import com.googlecode.jsonrpc4j.HttpException;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.googlecode.jsonrpc4j.ProxyUtil;

/**
 * 
 * @author ctg
 * @date 2016年2月25日
 */
public class Test2 {

	@Test
	public void http404() throws MalformedURLException {
		JsonRpcHttpClient jsonRpcHttpClient = new JsonRpcHttpClient(new URL(
				"http://127.0.0.1:8080/error"));
		UserService service = ProxyUtil.createClientProxy(
				UserService.class.getClassLoader(), UserService.class,
				jsonRpcHttpClient);

		try {
			service.getUserCount();
			Assert.fail();
		} catch (HttpException e) {
			Assert.assertTrue(e.getMessage().contains("404 Not Found"));
		}
	}
}
