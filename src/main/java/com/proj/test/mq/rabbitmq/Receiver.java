package com.proj.test.mq.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Receiver {
	private static final String QUEUE_NAME = "hello";

	public static void main(String[] args) throws IOException, TimeoutException, ShutdownSignalException,
			ConsumerCancelledException, InterruptedException {
//		 simpleRecive();
//		 pollReciver();
		pollReciverAck();
	}

	/**
	 * 简单的消息消费
	 * 
	 * @throws IOException
	 * @throws TimeoutException
	 * @throws ShutdownSignalException
	 * @throws ConsumerCancelledException
	 * @throws InterruptedException
	 */
	private static void simpleRecive() throws IOException, TimeoutException, ShutdownSignalException,
			ConsumerCancelledException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(5672);
		// 创建连接
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		// 定义队列，消费者可能比生产者先启动，为了保证消费者能连接
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true, consumer);
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println("<<< 收到消息：" + message);
			if (message != null && "ok".equals(message)) {
				channel.close();
				connection.close();
				break;
			}
		}
	}

	private static void pollReciver() throws IOException, TimeoutException, ShutdownSignalException,
			ConsumerCancelledException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(5672);
		// 创建连接
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		// 定义队列，消费者可能比生产者先启动，为了保证消费者能连接
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true, consumer);
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println("<<< 收到消息：" + message);
			if (message != null && "ok".equals(message)) {
				channel.close();
				connection.close();
				break;
			} else {
				for (char c : message.toCharArray()) {
					if (c == '.') {
						System.out.println("[" + message + "] :date:" + System.currentTimeMillis() + "执行了.");
						Thread.sleep(1000);
					}
				}
			}
		}
	}

	/**
	 * 如果消息客户端突然关闭，由于没有发送应答。则在下一次启动时，会重新获取消息
	 * 
	 * @throws IOException
	 * @throws TimeoutException
	 * @throws ShutdownSignalException
	 * @throws ConsumerCancelledException
	 * @throws InterruptedException
	 */
	private static void pollReciverAck() throws IOException, TimeoutException, ShutdownSignalException,
			ConsumerCancelledException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(5672);
		// 创建连接
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		// 定义队列，消费者可能比生产者先启动，为了保证消费者能连接
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		QueueingConsumer consumer = new QueueingConsumer(channel);
		boolean autoAck = false;// 启动消息应答机制,默认为true 关闭了
		channel.basicConsume(QUEUE_NAME, autoAck, consumer);
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println("<<< 收到消息：" + message);
			if (message != null && "ok".equals(message)) {
				// 关闭之前，需要发送消息应答
				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
				channel.close();
				connection.close();
				break;
			} else {
				for (char c : message.toCharArray()) {
					if (c == '.') {
						System.out.println("[" + message + "] :date:" + System.currentTimeMillis() + "执行了.");
						Thread.sleep(1000);
					}
				}
				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			}
		}
	}
}
