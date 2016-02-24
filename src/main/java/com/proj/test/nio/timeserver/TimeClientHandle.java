package com.proj.test.nio.timeserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimeClientHandle implements Runnable {
	private String host;
	private int port ;
	private Selector selector;
	private SocketChannel socketChannel;
	private volatile boolean stop;
	
	public  TimeClientHandle(String host,int port) {
		this.host = host;
		this.port = port;
		try {
			selector = Selector.open();
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@Override
	public void run() {
		try {
			doConnect();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("connection exception./.");
			System.exit(1);
		}
		
		while(!stop){
			try {
				selector.select(1000);
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectedKeys.iterator();
				SelectionKey key = null;
				while(it.hasNext()){
					key = it.next();
					it.remove();
					handleInput(key);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void handleInput(SelectionKey key) throws IOException {
		SocketChannel sc=null;
		if (key.isValid()) {
			//判断是否连接成功
			sc =(SocketChannel)key.channel();
			if (key.isConnectable()) {
				if (sc.finishConnect()) {
					sc.register(selector, SelectionKey.OP_READ);
					doWrite(sc);
				} else{ 
					System.out.println("sc.no finishConnection exception./.");
					System.exit(1);//连接失败，退出
				}
				
			}
		}
			if (key.isReadable()) {
				ByteBuffer readBuffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(readBuffer);
				if (readBytes > 0) {
					readBuffer.flip();
					byte[] bytes = new byte[readBuffer.remaining()];
					readBuffer.get(bytes);
					String body = new String(bytes,"UTF-8");
					System.out.println("Now is :"+body);
					this.stop = true;//获得远程响应数据,关闭连接
				} else if (readBytes < 0){
					key.cancel();//对端链路关闭
					sc.close();
					System.out.println("read byte <0");
				} else {
					System.out.println("read byte =0");//读到0 字节，忽略
				}
			}
	}

	private void doConnect() throws IOException {
		if (socketChannel.connect(new InetSocketAddress(host, port))) {
			socketChannel.register(selector, SelectionKey.OP_READ);
			System.out.println("直接连接成功.");
			doWrite(socketChannel);
			System.out.println("client 消息发送完成.");
		}else {
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
		}
	}

	private void doWrite(SocketChannel sc) throws IOException {
		byte[] req = "QUERY TIME ORDER".getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
		writeBuffer.put(req);
		writeBuffer.flip();
		sc.write(writeBuffer);
		if (!writeBuffer.hasRemaining()) {
			System.out.println("Send order 2 server succeed.");
		}
	}

}
