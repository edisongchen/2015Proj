package com.proj.webservice.rest.user;

import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.ctg.dubbo_hello_api.User;
import com.ctg.dubbo_hello_api.UserRestService;
import com.ctg.dubbo_hello_api.UserService;


@Path("/user")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Service("userRestService")
public class UserRestServiceImpl implements UserRestService{

	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GET
	@Path("{id:\\d+}")
	public User getUser(@Min(value=1L,message="User id must more than 1")
						@PathParam("id")Long id) {
		System.out.println("invoke UserRestServiceImpl"+RpcContext.getContext().getRemoteAddressString());
		return userService.getUser(id);
	}

	@GET
	@Path("/testSimple")
	public String testSimple() {
		return "amdin";
	}
	
}
