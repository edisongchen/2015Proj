/**
 * 
 */
package com.proj.controller.jsonrpc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.googlecode.jsonrpc4j.spring.JsonServiceExporter;

/**
 * 
 * @author ctg
 * @date 2016年2月27日
 */
@RequestMapping("/rpc/user")
@Controller
public class UserRPCHandler {

	@Autowired
	@Qualifier("/UserService")
	private JsonServiceExporter userService;
	
	@RequestMapping("getInfo")
	public void getInfo(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
		//rpc 异常可以向上交给spring容器捕获	
	  userService.handleRequest(request, response);
	}
}
