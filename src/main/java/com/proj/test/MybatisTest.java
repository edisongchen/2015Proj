package com.proj.test;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.proj.common.util.ApplicationContextHelper;
import com.proj.dao.mybatis.sys.IUserDao;
import com.proj.entity.sys.User;

public class MybatisTest {
	@Test
	public void testGetUser() throws SQLException{
//		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		//默认就是接口的名字(不是首字母小写这个问题坑惨)
		IUserDao userDao  = ApplicationContextHelper.getBean(IUserDao.class);
//		IUserDao userDao = ctx.getBean(IUserDao.class);
		User user = userDao.findById("1");
		System.out.println("///////////"+user.getName());
	}
}
