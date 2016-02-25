/**
 * 
 */
package com.proj.test.jsonrpc.spring;

import static junit.framework.Assert.assertSame;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.spring.JsonServiceExporter;
import com.proj.common.util.ApplicationContextHelper;

/**
 * 
 * @author ctg
 * @date 2016年2月25日
 */
public class SpringJsonRPCTEST {

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
	public void testSpring() throws Throwable{
		JsonRpcHttpClient client 
			= new JsonRpcHttpClient(new URL("http://127.0.0.1:8080/WEBDemo/MyService"));
		
		String properties = client.invoke("getUser", null, String.class);
		
		System.out.println(properties);
	}
	
	@Test
	public void testAPPLICATION(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 Object bean = ctx.getBean("/MyService");
		// System.out.println(bean instanceof JsonServiceExporter);
		 assertSame(JsonServiceExporter.class, bean.getClass());
	}
}
