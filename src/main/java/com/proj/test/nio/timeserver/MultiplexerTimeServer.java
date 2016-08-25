package com.proj.test.nio.timeserver;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;


public class MultiplexerTimeServer implements Runnable{
	private ServerSocketChannel servChannel;
	private volatile boolean stop;
	private Selector selector;
	public MultiplexerTimeServer(int port){
		try {
			selector = Selector.open();
			servChannel = ServerSocketChannel.open();
			servChannel.configureBlocking(false);
			//绑定端口，最大处理1024个连接,其余的阻塞
			servChannel.socket().bind(new InetSocketAddress(port),1024);
			servChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("the time server is start in port:"+port);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void stop(){
		this.stop = true;
	}
	
	@Override
	public void run() {
		while(!stop){
			try {
				selector.select(1000);//timeout 1000 ms
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectedKeys.iterator();
				SelectionKey key = null;
				while(it.hasNext()){
					key = it.next();
					it.remove();
					try {
						handleInput(key);
					} catch (Exception e) {
						if (key != null) {
							key.cancel();
							if (key.channel() !=null) {
							  key.channel().close();
							}
						}
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void handleInput(SelectionKey key) throws IOException {
		if (key.isValid()) {
			//处理接入的请求消息
			if (key.isAcceptable()) {
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_READ);
			}
			if (key.isReadable()) {
				//read the data
				SocketChannel sc = (SocketChannel) key.channel();
				ByteBuffer readBuffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(readBuffer);
				if (readBytes > 0) {
					readBuffer.flip();
					byte[] bytes = new byte[readBuffer.remaining()];
					readBuffer.get(bytes);
					String body = new String(bytes,"UTF-8");
					System.out.println("The time server receive order:"+body);
					String currentTime ="QUERY TIME ORDER".equals(body)?new Date().toString():
						"BAD ORDER";
					System.out.println("fff:"+currentTime);
					doWrite(sc,currentTime);
				}else if(readBytes <0 ){
					//对端链路关闭
					key.cancel();
					sc.close();
					System.out.println("read <0");
				}else {
					System.out.println("read 0");
					//读到0字节,忽略
				}
			}
		}
	}

	private void doWrite(SocketChannel channel, String response) throws IOException {
		if (response !=null && response.trim().length()>0) {
			byte[] bytes = response.getBytes();
			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);//put bytes to ByteBuffer
			writeBuffer.flip();
			channel.write(writeBuffer);
			System.out.println("writed response to client/");
		}
	}
}
