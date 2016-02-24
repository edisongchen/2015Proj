package com.proj.test.nio.timeserver;

/**
*	java nio timeserver	
* @author: Administrator
* @version: 1.0, 2016年2月23日
*/
public class TimeServer {
	public static void run(int port){
		MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
		new Thread(timeServer,"timeserver-001").start();
	}
	public static void main(String[] args) {
		TimeServer.run(2223);
	}
}
