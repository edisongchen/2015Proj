package com.proj.test.mq.rabbitmq;

import java.util.Scanner;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender4Work {
private static final String QUEUE_NAME="hello";
	
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(5672);
		//创建连接
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//定义队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		for(int i=0;i<10;i++){
			String message = ""+i;
			int r=(int)(Math.random()*10);
			for(int j=0;j<r;j++){
				message +=".";
			}
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println(" >>> 发送："+message);
		}
			
		
	}
}
