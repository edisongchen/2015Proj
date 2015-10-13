package com.proj.util;

import com.proj.entity.Author;
import com.proj.entity.Book;

public class FactoryTest {
	// private constructor
	private FactoryTest() {
		System.out.println("//////////////////////////private constructor method");
	}

	private static FactoryTest	factoryTest;
	private static Author author = new Author(); 


	public static FactoryTest getInstance() {
		System.out.println("//////////////////FactoryTest.getInstance");
		if (factoryTest == null) {
			return new FactoryTest();
		}
		return factoryTest;
	}
	
	public  Author getAuthorInstance(){
		System.out.println("//////////FactoryTest.getAuthorInstance");
		return author;
	}
}
