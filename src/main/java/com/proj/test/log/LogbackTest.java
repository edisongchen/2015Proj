package com.proj.test.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTest {
	private static Logger logger = LoggerFactory.getLogger(LogbackTest.class);
	public static void main(String args[]){
		System.out.println("..............");
		logger.debug("this is debug level logged by Logback");
		logger.info("this is info level logged by logback");
		logger.error("this is error level logged by logback");
	}
}
