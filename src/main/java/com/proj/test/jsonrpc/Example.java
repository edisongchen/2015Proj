/**
 * 
 */
package com.proj.test.jsonrpc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import com.proj.common.mapper.JsonMapper;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;

/**
 * 
 * @author ctg
 * @date 2016年2月24日
 */
public class Example {
	@Test
	public  void test1() {
		URL serverURL = null;
		try {
			serverURL = new URL("http://jsonrpc.example.com:8080");
			JSONRPC2Session mySession = new JSONRPC2Session(serverURL);
			mySession.getOptions().setRequestContentType("application/json+rpc");
			String method = "getServerTime";
			int requestID=1;
			JSONRPC2Request req = new JSONRPC2Request(method, requestID);
			
			//send request
			JSONRPC2Response response = mySession.send(req);
			if (response.indicatesSuccess()) {
				System.out.println("response:\n"+response);
			}else {
				System.out.println(response.getError().getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test2() throws Throwable{
		try {
			JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://example.com/UserService.json"));
			User user = client.invoke("createUser", new Object[] { "bob", "the builder" }, User.class);
			System.out.println(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void loadTestJSONRpcHttpClient() throws Throwable{
		JsonRpcHttpClient client 
		    = new JsonRpcHttpClient(new URL("http://127.0.0.1:8080/WEBDemo/MyService.json"));
		Map<String,String> headers = new HashMap<String,String>();

        headers.put("name", "123");

        client.setHeaders(headers);

        User properties = client.invoke("getUser", null, User.class);

        System.out.println(properties);
	}
}
