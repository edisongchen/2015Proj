package com.proj.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.proj.dao.mybatis.IUserDao;
import com.proj.entity.User;

public class MybatisTest {
	@Test
	public void testGetUser() throws SQLException{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		//默认就是接口的名字(不是首字母小写这个问题坑惨)
		IUserDao dao=ctx.getBean("IUserDao", IUserDao.class);
		List<User> users = dao.findAll(new User());
		for(User u: users){
			System.out.println(u);
		}
		System.out.println(users.size());
	}
}
