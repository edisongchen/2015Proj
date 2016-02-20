package com.proj.test.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
*	Netty UDP Client
*   无连接的。通过	channel.closeFuture().await 设置超时响应
* @author: Administrator
* @version: 1.0, 2016年2月20日
*/
public class NettyUDPClient {
	public static void main(String[] args) {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioDatagramChannel.class)
				.option(ChannelOption.SO_BROADCAST, true)
				.handler(new NettyUDPClientHandler());
			Channel channel = b.bind(0).sync().channel();
			System.out.println("fff1");
			channel.writeAndFlush(new DatagramPacket(
					Unpooled.copiedBuffer("ff", CharsetUtil.UTF_8), 
					new InetSocketAddress("127.0.0.1", 9999))).sync();
			System.out.println("fff2");
			if (!channel.closeFuture().await(4000)) {
				System.out.println("超时5秒、、、、、");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			System.out.println("fff3");
			group.shutdownGracefully();
		}
		
	}
}
