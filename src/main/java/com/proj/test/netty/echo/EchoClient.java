/**
 * 
 */
package com.proj.test.netty.echo;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 
 * @author ctg
 * @date 2016年1月28日
 */
public class EchoClient {
	private final String host;
	private final int port;
	
	public EchoClient(String host,int port){
		this.host = host;
		this.port = port;
	}
	
	public static void main(String[] args) {
			new EchoClient("127.0.0.1", 9090).start();

	}

	/**
	 * 
	 */
	private void start() {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
			.channel(NioSocketChannel.class)
			.remoteAddress(new InetSocketAddress(host, port))
			.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new EchoClientHandler());
					
				}
			});
			
			ChannelFuture f = b.connect().sync(); //连接到远程,等待连接完成
			
			f.channel().closeFuture().sync(); //阻塞知道Channel关闭
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			group.shutdownGracefully();
		}
	}
}
