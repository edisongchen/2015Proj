package com.proj.test.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class NettyUDPClientHandler  extends SimpleChannelInboundHandler<DatagramPacket>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
		String response = packet.content().toString(CharsetUtil.UTF_8);
		System.out.println("get response:"+response);
		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext pCtx, Throwable pCause) throws Exception {
		System.out.println("get exception");
		super.exceptionCaught(pCtx, pCause);
	}
	
	
	

}
