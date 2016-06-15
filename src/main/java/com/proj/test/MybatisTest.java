package com.proj.test;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MybatisTest {
	@Test
	public void testGetUser() throws SQLException{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		//默认就是接口的名字(不是首字母小写这个问题坑惨)
	}
}
