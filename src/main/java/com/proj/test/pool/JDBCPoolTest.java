package com.proj.test.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JDBCPoolTest {
	Logger logger = LoggerFactory.getLogger(JDBCPoolTest.class);
	@Test
	public void tomcatJDBC(){
		try{   
		    //加载MySql的驱动类   
		    Class.forName("com.mysql.jdbc.Driver") ;
		    logger.debug("found jdbc driver");
		    String url = "jdbc:mysql://localhost:3306/webdemo";
		    String username = "root" ;
		    String password = "48STX2X" ;
		   Connection conn = DriverManager.getConnection(url, username, password);
		   logger.debug("get connection :{}",conn);
		   PreparedStatement ps=conn.prepareStatement("select * from users");
		   ResultSet rs = ps.executeQuery();
		   while(rs.next()){
			  String uname =  rs.getString("username");
			  logger.info(uname);
		   }
	    }catch(Exception e){   
	    	logger.warn(e.getMessage());
	    } 
	}
}
