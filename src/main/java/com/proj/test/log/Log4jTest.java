package com.proj.test.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jTest {
	static Logger logger = Logger.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		//it will be load auto Log4j.properties，if it customer names
		//set the line
//		PropertyConfigurator.configure("E:/2015Learn/2015Proj/src/main/resources/??.properties");
		// 记录debug级别的信息  
        logger.debug("This is debug message.test");  
        // 记录info级别的信息  
        logger.info("This is info message.");  
        // 记录error级别的信息  
        logger.error("This is error message."); 
        test();
	}
	
	public static void test(){
		logger.info("test2");
		logger.error("test3");
	}
}
