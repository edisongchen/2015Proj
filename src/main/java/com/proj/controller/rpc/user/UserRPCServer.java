package com.proj.controller.rpc.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.jsonrpc4j.JsonRpcBasicServer;
import com.googlecode.jsonrpc4j.spring.JsonServiceExporter;
import com.proj.test.jsonrpc.spring.MyService;
import com.proj.test.jsonrpc.spring.MyServiceImpl;

@Controller
@RequestMapping("/rpc")
public class UserRPCServer {
	@Autowired
    @Qualifier("/MyService")
	private JsonServiceExporter myService;
	
	@RequestMapping("/user.json")
	@ResponseBody
	public void userHandler(HttpServletRequest request,HttpServletResponse response){
		try {
			String id = request.getHeader("id");
			System.out.println("controller get headerId:"+id);
			System.out.println("myService-hashcode:"+myService);
			myService.setErrorResolver(JsonRpcBasicServer.DEFAULT_ERRROR_RESOLVER);
			myService.handleRequest(request, response);
//			myService.setErrorResolver(AnnotationsErrorResolver.INSTANCE);
		} catch (ServletException e) {
			System.out.println("handler catch ServletException exception:==============");
//			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("handler catch IOException exception:==============");
//			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("catched exception...");
		}
	}
	
	@RequestMapping("/user2.json")
	public JsonServiceExporter user2Handler(HttpServletRequest request,HttpServletResponse response){
		System.out.println("////");
		JsonServiceExporter serviceExporter = new JsonServiceExporter();
		serviceExporter.setService(new MyServiceImpl());
		serviceExporter.setServiceInterface(MyService.class);
		return serviceExporter;
	}
}
