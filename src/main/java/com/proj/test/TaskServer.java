package com.proj.test;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.ImmediateEventExecutor;

import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/2/1.
 */
public class TaskServer {
    private static final Logger logger = Logger.getLogger(TaskServer.class.getName());

    public  void bind(int port)throws Exception{

        //服务器线程组 用于网络事件处理 1.服务器接收客户端连接 2.处理SocketChannel网络读写
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ChannelGroup group = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
        try{
            //Server服务器启动
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)// 类似NIO中serverSocketChannel
                    .option(ChannelOption.SO_BACKLOG, 1024)// 配置TCP参数
                    .option(ChannelOption.TCP_NODELAY,true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new LoggingHandler(LogLevel.INFO))//配置日志输出
                    .childHandler(new ChildChannelHandler());// 最后绑定I/O事件的处理类
            //处理网络IO事件

            // 服务器启动后 绑定监听端口 同步等待成功 主要用于异步操作的通知回调 回调处理用的ChildChannelHandler
            ChannelFuture f = serverBootstrap.bind(port).sync();
            if (f.isSuccess()) {
                logger.info("启动Netty服务成功，端口号：" + port);
                System.out.println("启动task server服务成功");
            }
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            // 优雅退出 释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            System.out.println("服务器释放了线程资源...");
        }
    }


    /**
     * 网络事件处理器
     */
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
//            //添加对象解码器 负责对序列化POJO对象进行解码 设置对象序列化最大长度为1M 防止内存溢出
//            //设置线程安全的WeakReferenceMap对类加载器进行缓存 支持多线程并发访问  防止内存溢出
//            ch.pipeline().addLast(new ObjectDecoder(1024*1024*1024, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
//            //添加对象编码器 在服务器对外发送消息的时候自动将实现序列化的POJO对象编码
//            ch.pipeline().addLast(new ObjectEncoder());
            ByteBuf delimiter = Unpooled.copiedBuffer("&".getBytes());//分隔符
            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));//设置分割符解码器
//            ch.pipeline().addLast(new FixedLengthFrameDecoder(50));//设置定长解码器  长度为50
            ch.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));//设置字符串解码器 自动将报文转换成字符串
            ch.pipeline().addLast(new TaskServerHandler()); //设置网络事件处理器
        }

    }

    public static void main(String[] args)throws Exception{
        int port = 8001;
        new TaskServer().bind(port);

    }

//    public static void pushToClient(ChannelBuffer buf){
//        try{
//            synchronized(channelList){
//                for(Channel chn:getChannelList()){
//                    if(chn.isWritable()) {
//                        chn.write(buf);
//                    }
//                }
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
}
