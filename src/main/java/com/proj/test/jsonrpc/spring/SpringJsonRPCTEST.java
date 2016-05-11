/**
 * 
 */
package com.proj.test.jsonrpc.spring;

import static org.junit.Assert.assertFalse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.proj.entity.jsonrpc.User;

/**
 * 
 * @author ctg
 * @date 2016年2月25日
 */
public class SpringJsonRPCTEST {
	JsonRpcHttpClient client=null;
	Map<String,String> paramMap = null;
	ByteArrayOutputStream baos = null;
	ByteArrayInputStream bais = null;
	byte [] bytes = new byte[100];
	@Before
	public void before(){
		 try {
			 baos = new ByteArrayOutputStream();
			 bais = new ByteArrayInputStream(bytes);
			 paramMap = Maps.newHashMap();
			client = new JsonRpcHttpClient(new URL("http://127.0.0.1:8080/WEBDemo/testservlet/test2"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void testSpring() {
		
		String response;
		try {
			 Map<String,String> headers = new HashMap<String,String>();
			 headers.put("jsonrpc", "2.0");
			 headers.put("id", UUID.randomUUID().toString());
			 client.setHeaders(headers);
			 response = client.invoke("getUser", new Object[]{""}, String.class);
//			client.writeRequest("getUser", null, baos, "1");
//			String request = baos.toString("UTF-8");
//			System.out.println("request:"+request);
			
			System.out.println("response:"+response);
			
		} catch (Throwable e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testBeanParam() throws Throwable{
		//使用对象接收
		paramMap.put("userName", "1");;
		paramMap.put("password", "pwd");
		paramMap.put("firstName", "22");
		String response 
		   = client.invoke("testBeanParam", new Object[]{paramMap}, String.class);
		System.out.println("response:"+response);
	}
	
	@Test
	public void testMapParam() {
		paramMap.put("id", "fff");
		paramMap.put("id2", "fff2");
		String response = null;
		try {
			response = client.invoke("testMap", paramMap,String.class);
		} catch (Throwable e) {
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		System.out.println(response);
	}
	
	@Test
	public void testParam() throws Throwable{
		String [] param=new String[]{"name1"};
		paramMap.put("pwd2", "fff2");
		paramMap.put("name2", "fff");
		Map<String, String> map2 = Maps.newHashMap();
		map2.put("pwd2", "aaaa");
		List<String> list = Lists.newArrayList();
		list.add("2");
		list.add("f");
//		String response = client.invoke("getUser2", param, String.class);
//		User response2 = client.invoke("getUserEntity", null, User.class);
//		User response = client.invoke("getUserEntity2", paramMap, User.class);
//		User response = client.invoke("createUser", new String[]{"name1","passe"}, User.class);
		System.out.println(client.invoke("testListParam", new Object[]{list}, Object.class));
		User response = client.invoke("createUser", paramMap, User.class);
		System.out.println(response);
	}
	
	@Test
	public void testException (){
		try {
			String response = client.invoke("getUser", null, String.class);
			System.out.println(response);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private JsonNode readJSON(ByteArrayOutputStream baos)
			throws JsonProcessingException,
			IOException {
			return client.getObjectMapper().readTree(baos.toString());
		}
	@Test
	public void testInvokeNoParams(){
		 ByteArrayOutputStream baos=new ByteArrayOutputStream();
		 try {
			client.invoke("test", new Object[0], baos);
			JsonNode node = readJSON(baos);
			assertFalse(node.has("params"));
			
			client.invoke("test", (Object[])null, baos);
			node = readJSON(baos);
			assertFalse(node.has("params"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
