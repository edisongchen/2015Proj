package com.proj.test.mq.rabbitmq;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class Sender {

	private static final String QUEUE_NAME="hello";
	private static final String QUEUE_NAME_PERSISTENT="persistentQueue";
	
	public static void main(String[] args) throws Exception {
//		basicSend();
//		pollingSend();
		pollingSendMerge();
	}
	
	/**
	 * 基本的发送方法
	 * @throws IOException
	 * @throws TimeoutException
	 */
	private static void basicSend() throws IOException, TimeoutException{
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
	
	/**
	 * 轮询分发，
	 * @throws IOException
	 * @throws TimeoutException
	 */
	private static void pollingSend() throws IOException, TimeoutException{
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
	/**
	 * 轮询分发
	 * 要保证消息不丢失。将队列和消息标记为持久化
	 * @throws IOException
	 * @throws TimeoutException
	 */
	private static void pollingSendMerge() throws IOException, TimeoutException{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(5672);
		//创建连接
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//将队列标记为持久化
		boolean durable = true;
		//声明队列持久化
		channel.queueDeclare(QUEUE_NAME_PERSISTENT, durable, false, false, null);
		
		for(int i=0;i<10;i++){
			String message = ""+i;
			int r=(int)(Math.random()*10);
			for(int j=0;j<r;j++){
				message +=".";
			}
			//声明消息 持久化
			channel.basicPublish("", QUEUE_NAME_PERSISTENT, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
			System.out.println(" >>> 发送："+message);
		}
	}
	
}
