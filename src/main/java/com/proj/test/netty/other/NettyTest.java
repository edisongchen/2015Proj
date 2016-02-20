/**
 * 
 */
package com.proj.test.netty.other;

import java.nio.charset.Charset;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;

import org.junit.Test;

/**
 * 
 * @author ctg
 * @date 2016年1月29日
 */
public class NettyTest {

	@Test
	public void Multithread(){
		//Netty 的一个简单的多线程demo
		final Channel channel =null;
		final ByteBuf buf = Unpooled.copiedBuffer("your data",CharsetUtil.UTF_8).retain();
		
		Runnable write = new Runnable() {
			
			@Override
			public void run() {
				channel.writeAndFlush(buf.duplicate());
			}
		};
		
		Executor executor = Executors.newCachedThreadPool();
		executor.execute(write);
		executor.execute(write);
		
	}
	@Test
	public void ByteBufSlice(){
		Charset utf8 = Charset.forName("UTF-8");
		ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks",utf8);
		
		//ByteBuf sliced = buf.slice(0, 14); //true
		ByteBuf sliced = buf.copy(0, 4); //false
		System.out.println(sliced.toString(utf8));
		
		buf.setByte(0, (byte)'J');
		System.out.println(buf.getByte(0) == sliced.getByte(0) );
		
	}
	
	@Test
	public void testRW(){
		Charset utf8 = Charset.forName("UTF-8");
		ByteBuf buf = Unpooled.copiedBuffer("admin",utf8);
		System.out.println("reader index1:"+buf.readerIndex());
		System.out.println("write index1:"+buf.writerIndex());
		System.out.println((char)buf.readByte());
		buf.writeByte((byte)'?');
		System.out.println("write index2:"+buf.writerIndex());
		System.out.println("reader index2:"+buf.readerIndex());
		System.out.println((char)buf.readByte());
		System.out.println("reader index3:"+buf.readerIndex());
		System.out.println(buf.toString(utf8));
	}
}
