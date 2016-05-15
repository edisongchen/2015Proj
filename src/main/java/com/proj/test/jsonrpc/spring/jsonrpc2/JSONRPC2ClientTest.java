package com.proj.test.jsonrpc.spring.jsonrpc2;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;

public class JSONRPC2ClientTest {

	@Test
	public void simpleClientTest(){
		URL serverURL = null;
		try {
			serverURL = new URL("http://127.0.0.1:8080/WEBDemo/rpc/user.json");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		JSONRPC2Session mySession = new JSONRPC2Session(serverURL);
		String method = "getUser";
		int requestID = 1;
		JSONRPC2Request request = new JSONRPC2Request(method, requestID);
		JSONRPC2Response response = null;	
		try {
			response =  mySession.send(request);
		} catch (JSONRPC2SessionException e) {
			e.printStackTrace();
		}
		
		if (response.indicatesSuccess()) {
			System.out.println("success:"+response.getResult());
		}else {
			System.out.println("error:"+response.getError().getMessage());
		}
	}
}
