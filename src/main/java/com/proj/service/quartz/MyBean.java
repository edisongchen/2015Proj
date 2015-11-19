package com.proj.service.quartz;

import org.springframework.stereotype.Component;

@Component("myBean")
public class MyBean {
	public void doSomething(){
		System.out.println("doSomething////////////////////////////////////");
	}
}
