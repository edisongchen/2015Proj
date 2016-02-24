/**
 * 
 */
package com.proj.controller.jsonrpc;

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
public class HelloWorldServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private JsonRpcServer rpcServer = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		rpcServer = new JsonRpcServer(new HelloWorldServlet(),HelloWorldServlet.class);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	}
	
	
}
