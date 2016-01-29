/**
 * 
 */
package com.proj.test.netty.other;

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
}
