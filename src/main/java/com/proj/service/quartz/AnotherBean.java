package com.proj.service.quartz;

import org.springframework.stereotype.Component;

@Component("anotherBean")
public class AnotherBean {

	public void printAnotherMessage() {
		System.out.println(this.getClass().getSimpleName() +"printAnotherMessage //////////");
	}
}
