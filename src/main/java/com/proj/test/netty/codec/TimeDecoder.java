package com.proj.test.netty.codec;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class TimeDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
			throws Exception {
		if (in.readableBytes() <4) {
			System.out.println("<4");
			return;
		}
		out.add(in.readBytes(4));
	}

}
