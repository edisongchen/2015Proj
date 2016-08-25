package com.proj.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class UDPSeverHandler extends SimpleChannelInboundHandler<DatagramPacket>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
		
		System.out.println(this);
		System.out.println("read socket");
		ByteBuf buf = packet.copy().content();
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        ctx.writeAndFlush(new DatagramPacket(
        		Unpooled.copiedBuffer("response:"+body,CharsetUtil.UTF_8), packet.sender()));
        System.out.println(body);
	}

	@Override
	public void channelRegistered(ChannelHandlerContext pCtx) throws Exception {
		System.out.println("////regitser");
		super.channelRegistered(pCtx);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext pChannelhandlercontext) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("/////add");
		super.handlerAdded(pChannelhandlercontext);
	}
	
	
}
