/**
 * 
 */
package com.proj.test.jsonrpc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.jsonrpc4j.JsonRpcServer;

/**
 * 
 * @author ctg
 * @date 2016年2月24日
 */
public class ServiceJsonRpcServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private JsonRpcServer rpcService = null; 
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("init....");
		rpcService = new JsonRpcServer(new UserServiceImpl(),UserService.class);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("....get");
		rpcService.handle(request, response);
	}
	
	

}
