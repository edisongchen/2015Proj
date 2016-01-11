package com.proj.secutiry.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm2 implements Realm{

	@Override
	public String getName() {
		return "MyRealm2";
	}

	@Override
	public boolean supports(AuthenticationToken pToken) {
		return pToken instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken pToken) throws AuthenticationException {
		String username = (String)pToken.getPrincipal();
		String password = (String)pToken.getCredentials();
		if (!"root".equals(username)) {
			 throw new UnknownAccountException();
		}
		if (!"secret".equals(password)) {
			throw new IncorrectCredentialsException();
		}
		return new SimpleAuthenticationInfo(username, password, getName());
	}

}
