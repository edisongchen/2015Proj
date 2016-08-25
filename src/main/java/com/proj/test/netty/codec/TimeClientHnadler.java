package com.proj.test.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TimeClientHnadler extends SimpleChannelInboundHandler  {
	private ByteBuf buf;
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		buf = ctx.alloc().buffer(4);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext pChannelhandlercontext) throws Exception {
		buf.release();
		buf = null;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf m= (ByteBuf)msg;
		buf.writeBytes(m);
		m.release();
		while (buf.readableBytes() >= 4 ) {
			System.out.println(buf.readInt());
		}
		ctx.close(); 
	}

}
