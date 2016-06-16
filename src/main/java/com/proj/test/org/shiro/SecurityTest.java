package com.proj.test.org.shiro;

import com.proj.service.SystemService;

public class SecurityTest {

	public static void main(String[] args) {
		boolean flag=SystemService.validatePassword("admin", "c05aaaaf4a263f2f2a462d7e3fceae13c3cd95b7b02d2afaee6a105e");
		System.out.println(flag);
		
	}
}
