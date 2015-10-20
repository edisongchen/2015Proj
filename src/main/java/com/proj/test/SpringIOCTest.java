package com.proj.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.proj.entity.Author;
import com.proj.entity.Book;
import com.proj.util.FactoryTest;

public class SpringIOCTest {
	@Test
	public void testGetBeanByApplicationContext(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		Author author = ctx.getBean("author2",Author.class);
		System.out.println(author);
		Book book = ctx.getBean("book", Book.class);
		System.out.println(book);
		System.out.println("============");
		//getBean by alias
		Book book2 = ctx.getBean("book-alias-test", Book.class);
		System.out.println(book2);
		FactoryTest factoryTest=ctx.getBean("factoryTest",FactoryTest.class);
		System.out.println(factoryTest);
	
	}
	@Test
	public void testGetBeanByAnnotation(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		Author author = ctx.getBean("facAuthor",Author.class);
		System.out.println(author);
		System.out.println("===========================");
		Author author2 = ctx.getBean("author2",Author.class);
		System.out.println(author2);
	}
}
