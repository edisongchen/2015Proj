package com.proj.test;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/2/1.
 */
public class TaskClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private static final Logger logger = Logger.getLogger(TaskClientHandler.class.getName());

    private  ByteBuf firstMessage;
    public TaskClientHandler(){
        byte[] req ="hello task server".getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }

	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String content = "Netty is a NIO client server framework which enables quick and easy development of network applications &such as protocol servers and clients";
//        ctx.writeAndFlush(firstMessage);
//        String content = "";
        ctx.writeAndFlush(Unpooled.copiedBuffer(content.getBytes()));
        System.out.println("客户端active");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("客户端收到服务器响应数据：");
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req,"UTF-8");
//        System.out.println(body);
        System.out.println(msg.toString());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        System.out.println("客户端收到服务器响应数据处理完成");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        logger.warning("Unexpected exception from downstream:"+cause.getMessage());
//        NettyChannelMap.remove((io.netty.channel.socket.SocketChannel) ctx.channel());
        ctx.close();
        System.out.println("客户端异常退出");
    }

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg)
			throws Exception {
		System.out.println("//////client read from channel:"+msg.toString());
	}
}
