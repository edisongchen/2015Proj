/**
 * 
 */
package com.proj.test.jsonrpc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

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
		rpcService = new JsonRpcServer(new ReceivChannel(),ReceivChannel.class);
	}

}
