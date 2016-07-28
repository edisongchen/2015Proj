package com.proj.test.mq.rabbitmq;

import java.util.Scanner;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {

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
		//发送消息
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()){
			String message = scanner.nextLine();
			//退出
			if (message !=null && "quit".equals(message)) {
				channel.close();
				connection.close();
				break;
			} else {
				//发送消息
				channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
				System.out.println(" >>> 发送："+message);
			}
			
		}
		
	}
	
}
