/**
 * 
 */
package com.proj.test.netty.echo;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 
 * @author ctg
 * @date 2016年1月28日
 */
public class EchoServer {
	private final int port;
	
	public EchoServer(int port) {
		this.port = port;
	}
	
	public static void main(String[] args) {
			try {
				new EchoServer(9090).start();
			} catch (Exception e) {
				e.printStackTrace();
			} 

	}

	/**
	 * @throws Exception 
	 * 
	 */
	private void start() throws Exception {
		NioEventLoopGroup group = new NioEventLoopGroup();
		ServerBootstrap b = new ServerBootstrap();
		try {
		b.group(group)
		.channel(NioServerSocketChannel.class) //使用Nio的传输Channel，对应的还有OioServerSocketChannel
		.localAddress(new InetSocketAddress(port))
		.childHandler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				//添加Handler到Channel的pipeline
				ch.pipeline().addLast(new EchoServerHandler());
			}
			
		});
		
		ChannelFuture f;
			f = b.bind().sync(); //绑定一服务器，sync等待服务器关闭
			System.out.println(EchoServer.class.getName() 
					+" started and listen on "+f.channel().localAddress());
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully().sync();
		}
		
		
	}
}
