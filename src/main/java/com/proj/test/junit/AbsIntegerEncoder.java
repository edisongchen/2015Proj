/**
 * 
 */
package com.proj.test.junit;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

/**
 * 
 * @author ctg
 * @date 2016年2月16日
 */
public class AbsIntegerEncoder extends MessageToMessageDecoder<ByteBuf>{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		while(in.readableBytes() >= 4){
			int value = Math.abs(in.readInt());
			out.add(value);
		}
	}

}
