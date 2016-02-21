package com.proj.test.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class TimeEncoder extends MessageToByteEncoder<WindowsTime>  {

	@Override
	protected void encode(ChannelHandlerContext ctx, WindowsTime obj, ByteBuf buf) throws Exception {
		System.out.println(obj.getHhmm());
		buf.writeInt(200);
	}

	

}
