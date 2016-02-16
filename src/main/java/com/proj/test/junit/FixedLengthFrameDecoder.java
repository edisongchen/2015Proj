/**
 * 
 */
package com.proj.test.junit;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 
 * @author ctg
 * @date 2016年2月16日
 */
public class FixedLengthFrameDecoder extends ByteToMessageDecoder{

	private final int frameLength;
	
	public FixedLengthFrameDecoder(int frameLength){
		if (frameLength <= 0 ) {
			throw new IllegalArgumentException("framelength <=0");
		}
		this.frameLength = frameLength;
	}
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		if (in.readableBytes() >= frameLength ) {
			ByteBuf buf = in.readBytes(frameLength);
			out.add(buf);
		}
	}

}
