package com.proj.common.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Service;

@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter{

	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String userName = getUsername(request);
		String password = getPassword(request);
		if (password == null) {
			password ="";
		}
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		System.out.println("userName:"+userName+" password:"+password);
		return new UsernamePasswordToken(userName, password.toCharArray(),rememberMe,host);
	}

}
