/**
 * 
 */
package com.proj.test.jsonrpc;

import java.net.MalformedURLException;
import java.net.URL;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;

/**
 * 
 * @author ctg
 * @date 2016年2月24日
 */
public class Example {
	public static void main(String[] args) {
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
}
