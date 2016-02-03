package com.proj.test;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/2/1.
 * server端网络事件处理类
 */
public class TaskServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = Logger.getLogger(TaskServerHandler.class.getName());
    @Override
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("收到socket注册信息,连接IP是:" + channelHandlerContext.channel().remoteAddress());
        String content = "已收到消息";
        //NettyChannelMap.add("001",(SocketChannel)channelHandlerContext.channel());
        ByteBuf response = Unpooled.copiedBuffer(content.getBytes());
        channelHandlerContext.writeAndFlush(response);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("服务器读取到客户端请求...");
//        ByteBuf buf =(ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req,"UTF-8");
        //接收客户端的消息
//        System.out.println("服务器接收到客户端的数据:" + body);
        System.out.println("服务器接收到客户端的数据:" + msg.toString());
        //消息处理
        String content = msg.toString() + new Date();

        //响应
        ByteBuf response = Unpooled.copiedBuffer(content.getBytes());
        ctx.writeAndFlush(response);
        System.out.println("服务器做出了响应");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        System.out.println("服务器readComplete 响应完成");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        logger.warning("服务器异常退出"+cause.getMessage());
        ctx.close();
        System.out.println("服务器异常退出"+cause.getMessage());
    }
    @Override
    public void channelActive(final ChannelHandlerContext ctx){

    }
}
