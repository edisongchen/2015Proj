/**
 * 
 */
package com.proj.test.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 
 * @author ctg
 * @date 2016年1月28日
 */
//sharable 标识这个类的实例之间可以在Channel里面共享
@Sharable
public class EchoServerHandler2 extends ChannelInboundHandlerAdapter{

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		//冲刷所有待审消息到远程节点,关闭通道后，操作完成
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
		System.out.println("read complete2");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf in =(ByteBuf)msg;
		System.out.println("Server2 received :" +in.toString(CharsetUtil.UTF_8));
		ctx.write(in); //将接收的消息返回给发送者，并没有冲刷数据.将在下一个handler调用
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
