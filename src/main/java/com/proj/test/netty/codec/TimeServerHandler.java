package com.proj.test.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

	 @Override  
     public void channelActive(ChannelHandlerContext ctx) throws Exception {  
         ctx.pipeline().write(new WindowsTime()); 
         ctx.pipeline().write(new WindowsTime()).channel().pipeline().flush();
         System.out.println("active...");
     }

	@Override
	public void channelReadComplete(ChannelHandlerContext pCtx) throws Exception {
		System.out.println("read Complete");
		super.channelReadComplete(pCtx);
	}  
       
}
