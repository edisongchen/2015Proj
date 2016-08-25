package com.proj.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import java.util.List;

public class ToIntegerDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		System.out.println("geted ....");
		while(in.readableBytes() >= 4){
			System.out.println("convert..");
//			out.add(in.readInt());
			int read = in.readInt();
			System.out.println(read);
		}
		
		//ReferenceCountUtil.release(in);
		ctx.writeAndFlush(Unpooled.copiedBuffer("geted",CharsetUtil.UTF_8));
	}

}
