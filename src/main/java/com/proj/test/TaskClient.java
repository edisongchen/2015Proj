package com.proj.test;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016/2/1.
 * 客户端
 */
public class TaskClient {

    public void connect(int port,String host)throws Exception {
        //客户端线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch)
                                throws Exception {
//                            //添加POJO对象解码器 禁止缓存类加载器
//                            ch.pipeline().addLast(new ObjectDecoder(1024, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
//                            //设置发送消息编码器
//                            ch.pipeline().addLast(new ObjectEncoder());
//                            ByteBuf delimiter = Unpooled.copiedBuffer("&".getBytes());
//                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
//                            ch.pipeline().addLast(new FixedLengthFrameDecoder(10));//设置定长解码器
                            ch.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));//设置字符串解码器
                            ch.pipeline().addLast(new TaskClientHandler());//设置客户端网络处理器
                        }
                    });

            //连接服务器 同步等待链接成功
            ChannelFuture f = bootstrap.connect(host, port).sync();
            if(f.isSuccess()){
                System.out.println("客户端连接服务器成功");
            }
            //同步客户端通道关闭
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
            System.out.println("客户端释放了连接");
        }
    }

    public static void main(String[] args)throws Exception{
        int port = 8001;
        String host = "127.0.0.1";
        new TaskClient().connect(port,host);
    }
}
