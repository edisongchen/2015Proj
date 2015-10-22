package com.proj.test;


import java.sql.Connection;
import java.sql.DriverManager;


public class connectionDB {

	/**
	 * @param args
	 */
	private static connectionDB db=null;
	
	Connection conn=null;
	
	//无参数构造方法
	private connectionDB(){	}
	
	public static connectionDB getInstance(){
		if(db==null){
			db=new connectionDB();
		}
		return db;	
	}
//获取connection的方法
public Connection getConnection(){
	try {
		//加载数据库的驱动
		Class.forName("com.mysql.jdbc.Driver");
		
		//获得链接
		conn=DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/fsj"
				,"root"
				,"48STX2X");
		//jdbc:子协议：子名称//主机名///端口号//数据库名
	} 
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return conn;
	
}
	
	public static void main(String[] args) {
		connectionDB db1=connectionDB.getInstance();
		if(db1.getConnection()!=null){
			System.out.println("OK");
		}
		else{
			System.out.println("sorry");
		}

	}

}
