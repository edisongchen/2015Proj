/**
 * 
 */
package com.proj.test.jsonrpc.spring;

import static junit.framework.Assert.assertSame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javassist.expr.NewArray;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.spring.JsonServiceExporter;
import com.proj.test.jsonrpc.User;
import com.thoughtworks.xstream.mapper.Mapper.Null;

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
	public void test2() throws Throwable{
		JsonRpcHttpClient client 
	        = new JsonRpcHttpClient(new URL("http://127.0.0.1:8080/WEBDemo/jsonrpc"));
		Map<String,String> headers = new HashMap<String,String>();
		
		headers.put("name", "123");
		
		client.setHeaders(headers);
		
		String properties = client.invoke("createUser", null, String.class);
		
		System.out.println("result:"+properties);
	}
	@Test
	public void testSpring() {
		
		String properties;
		try {
			 Map<String,String> headers = new HashMap<String,String>();
			 headers.put("jsonrpc", "2.0");
			 headers.put("id", UUID.randomUUID().toString());
			 client.setHeaders(headers);
			properties = client.invoke("getUser", new Object[]{""}, String.class);
			client.writeRequest("getUser", null, baos, "1");
			String request = baos.toString("UTF-8");
			System.out.println("request:"+request);
			
			System.out.println(properties);
			
		} catch (Throwable e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testAPPLICATION(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 Object bean = ctx.getBean("/MyService");
		// System.out.println(bean instanceof JsonServiceExporter);
		 assertSame(JsonServiceExporter.class, bean.getClass());
	}
	
	
	@Test
	public void testBeanParam() throws Throwable{
		//使用对象接收
		paramMap.put("userName", "1");;
		paramMap.put("password", "pwd");
		paramMap.put("firstName", "22");
		String response 
		   = client.invoke("testBeanParam", new Object[]{paramMap}, String.class);
		System.out.println(response);
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
}
