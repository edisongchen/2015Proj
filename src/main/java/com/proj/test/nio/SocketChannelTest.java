/**
 * 
 */
package com.proj.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.junit.Test;

/**
 * SocketChannel是一个连接到TCP网络套接字的通道。
 * 打开一个SocketChannel并连接到互联网上的某台服务器
 * 一个新连接到达ServerSocketChannel时,会创建一个SocketChannel
 * @author ctg
 * @date 2016年1月27日
 */
public class SocketChannelTest {

	@Test
	public void testOpenChannel(){
		SocketChannel socketChannel = null;
		try {
			socketChannel = SocketChannel.open();
			socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));
			ByteBuffer buf = ByteBuffer.allocate(100);
			int size = socketChannel.read(buf);
			System.out.println(size);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (socketChannel !=null) {
				try {
					socketChannel.close();
					System.out.println("closed....");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
