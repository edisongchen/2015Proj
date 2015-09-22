package com.proj.spring.base;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.proj.entity.User;

public class ApplicationContextDemo {
	@Test
	public  void getBean() {
		String config="classpath:/resources/applicationContext.xml";
		ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
		User user=ctx.getBean("user", User.class);
		System.out.println(user);
	}
}
