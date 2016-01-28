/**
 * 
 */
package com.proj.test.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * 
 * @author ctg
 * @date 2016年1月28日
 */
//这个类的实例 可以在Channel中共享
@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in)
			throws Exception {
		//记录收到的消息
		System.out.println("Client received: "+in.toString(CharsetUtil.UTF_8));
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//当被通知该Channel是活动的时候就发送消息
		ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!",CharsetUtil.UTF_8) );
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	

}
