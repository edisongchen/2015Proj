package com.proj.test.nio.timeserver;

public class TimeClient {
	public static void run(int port){
		new Thread(new TimeClientHandle("127.0.0.1",port),"TimeClient-001").start();
	}
	public static void main(String[] args) {
		TimeClient.run(2223);
	}
}
