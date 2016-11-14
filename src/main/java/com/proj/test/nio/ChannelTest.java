/**
 * 
 */
package com.proj.test.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


/**
 * 既可以从通道中读取数据，又可以写数据到通道。但流的读写通常是单向的
 * 通道可以异步地读写
 * 通道中的数据总是要先读到一个Buffer，或者总是要从一个Buffer中写入
 * FileChannel
 * DatagramChannel
 * SocketChannel
 * ServerSocketChannel
 * @author ctg
 * @date 2016年1月27日
 */
public class ChannelTest {
	
	@Test
	public void readFile(){
		try {
			RandomAccessFile aFile = new RandomAccessFile("file/file1.txt", "rw");
			FileChannel inChannel = aFile.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(8);
			while(inChannel.read(buf) !=-1){
				System.out.println("Read ...");
				buf.flip();//clear and ready next 
				while(buf.hasRemaining()){
					System.out.print((char)buf.get());
				}
				System.out.println();
				buf.clear();
			}
			aFile.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void FileChannel(){
		//FileChannel 总运行在阻塞模式下。使用之前必须打开它
		//但是只能通过InputStream,OutputStream,RandomAccessFile来获取
		//一个FileChannel实例
		//read,write,close,position,size,truncate,force
		String nowDate = " now :"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		ByteBuffer buf = ByteBuffer.allocate(50);
		//buf.clear();
		buf.put(nowDate.getBytes());
		File file = new File("file/file1.txt");
		FileOutputStream fos;
		FileChannel channel = null;
		try {
			fos = new FileOutputStream(file,true);
			channel = fos.getChannel();
			buf.flip();
			while(buf.hasRemaining()){
				channel.write(buf);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (channel !=null) {
				try {
					channel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
