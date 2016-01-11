package com.proj.test.org.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogInOut {
	private static Logger logger =LoggerFactory.getLogger(LogInOut.class);
	
	@Test
	public void loginTest(){
		Factory<org.apache.shiro.mgt.SecurityManager> factory 
		 = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("guest", "guest");
		try {
			logger.debug("subject name:{} ready login",token.getPrincipal());
			subject.login(token);
			logger.debug("subject name:{} login success",token.getPrincipal());
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		logger.debug("subject logout:{}",subject.getPrincipal());
		 subject.logout();
	}
	@Test
	public void customerMultiRealm (){
		Factory<org.apache.shiro.mgt.SecurityManager> factory 
		 = new IniSecurityManagerFactory("classpath:shiro/shiro-multi-realm.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("guest", "guest2");
		try {
			logger.debug("subject name:{} ready login",token.getPrincipal());
			subject.login(token);
			logger.debug("subject name:{} login success",token.getPrincipal());
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		logger.debug("subject logout:{}",subject.getPrincipal());
		 subject.logout();
	
	}
	
	@Test
	public void jdbcRealm (){
		//TODO 连接池
		Factory<org.apache.shiro.mgt.SecurityManager> factory 
		 = new IniSecurityManagerFactory("classpath:shiro/shiro-jdbc-realm.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		try {
			logger.debug("subject name:{} ready login",token.getPrincipal());
			subject.login(token);
			logger.debug("subject name:{} login success",token.getPrincipal());
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		logger.debug("subject logout:{}",subject.getPrincipal());
		 subject.logout();
	
	}
	
	  @After
	    public void tearDown() throws Exception {
	        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
	        logger.debug("after junit test");
	    }
}
