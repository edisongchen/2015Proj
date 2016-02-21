package com.proj.test.netty.codec;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeServer {
	public static void main(String[] args) throws InterruptedException {
		 EventLoopGroup boss = new NioEventLoopGroup();  
	        EventLoopGroup worker = new NioEventLoopGroup();  
	          
	        try {  
	            ServerBootstrap b = new ServerBootstrap();  
	            b.group(boss, worker);  
	            b.channel(NioServerSocketChannel.class);     
	            b.childHandler(new ChannelInitializer<NioSocketChannel>(){  
	  
	                @Override  
	                protected void initChannel(NioSocketChannel ch)  
	                        throws Exception {  
	                    // TODO Auto-generated method stub  
	                    ch.pipeline().addLast(new TimeServerHandler());  
	                    ch.pipeline().addFirst(new TimeEncoder());  
	                }  
	                  
	            });  
	            ChannelFuture f = b.bind(90).sync();  
	            f.channel().closeFuture().sync();  
	        } finally {  
	            boss.shutdownGracefully();  
	            worker.shutdownGracefully();  
	        }  
	}
}
