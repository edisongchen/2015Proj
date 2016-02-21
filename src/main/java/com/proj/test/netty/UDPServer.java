package com.proj.test.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class UDPServer {
	
	private static Channel channel = null;
	
	public static void init() throws InterruptedException {
	    Bootstrap b = new Bootstrap();
	    EventLoopGroup group = new NioEventLoopGroup();
	    b.group(group)
	        .channel(NioDatagramChannel.class)
	        .option(ChannelOption.SO_BROADCAST, true)
	        .handler(new ChannelInitializer<Channel>() {

				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(new ToIntegerDecoder());
					ch.pipeline().addLast(new UDPSeverHandler());
				}
			});
	 
	        Channel channel = b.bind(9999).sync().channel();
	        channel.closeFuture().await();
	  }
	public static void main(String[] args) {
		try {
			UDPServer.init();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
