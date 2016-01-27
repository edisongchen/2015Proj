/**
 * 
 */
package com.proj.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Date;

/**
 * 
 * @author ctg
 * @date 2016年1月27日
 */
public class DatagramChannelTest {
	
	public static void send(){
		String nowData= new Date().toString();
		ByteBuffer  buf = ByteBuffer.allocate(100);
		buf.clear();
		buf.put(nowData.getBytes());
		buf.flip();
		try {
			//getChannel().connect(new InetSocketAddress("127.0.0.1", 3344));
			int byteSend = getChannel().send(buf,new InetSocketAddress("127.0.0.1", 3344));
			System.out.println(byteSend);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (getChannel() !=null) {
					getChannel().close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	static DatagramChannel channel = null;
	public static DatagramChannel getChannel(){
		if(channel!=null){
			return channel;
		}
		try {
			channel = DatagramChannel.open();
			//channel.connect(new InetSocketAddress("127.0.0.1", 3344));
			channel.bind(new InetSocketAddress(3314));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return channel;
	}
	
	public static void main(String[] args) {
		send(); //Test with com/proj/test/io/UDPServer.java
	}
}
