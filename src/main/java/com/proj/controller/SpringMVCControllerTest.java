package com.proj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/springmvc")
public class SpringMVCControllerTest {
	private Logger logger = LoggerFactory.getLogger(SpringMVCControllerTest.class);
	
	@RequestMapping("/test")
	public String test(Model model){
		logger.info("success enter test method");
		model.addAttribute("info", "request springmvc success");
		return "test/hello";
	}
}
