/**
 * 
 */
package com.proj.test.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

/**
 * Scattering 分散
 * Gathering  聚集
 * 
 * 用于channel 与channel 之间(buffer是桥梁)
 * @author ctg
 * @date 2016年1月27日
 */
public class ScatterGatherTest {

	@Test
	public void ScatteringRead() throws IOException{
		//将channel中的数据读入多个buffer中
		ByteBuffer header = ByteBuffer.allocate(5);
		ByteBuffer body = ByteBuffer.allocate(10);
		ByteBuffer [] buffers ={header,body};
		RandomAccessFile rs = new RandomAccessFile("file/test2.txt", "rw");
		FileChannel fileChannel = rs.getChannel();
		long read = fileChannel.read(buffers);
		System.out.println(read);
		//将channel中的数据读入到了两个buffer中，当header 装满才能填充下一个buffer
		//这种方式不适用动态长度
	}
	
	@Test
	public void GatherWrite(){
		//将多个buffer中的数据写入channel
		ByteBuffer header = ByteBuffer.allocate(128);
		ByteBuffer body   = ByteBuffer.allocate(1024);
		//write data into buffers
		ByteBuffer[] bufferArray = { header, body };
		//channel.write(bufferArray);

	}
	
	@Test
	public void transferFrom() throws Exception{
		//如果channel中有一个是FileChannel,那么可以将数据从通道传入FileChannel中
		RandomAccessFile fromFile = new RandomAccessFile("file/from1.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();
		
		RandomAccessFile toFile = new RandomAccessFile("file/to1.txt", "rw");
		FileChannel toChannel = toFile.getChannel();
		
		long count = fromChannel.size();
		long postion = 0;
		System.out.println(count);
		long flag = toChannel.transferFrom(fromChannel, postion, count);
		System.out.println(flag);
		//transferTo() 与transferFrom类似只是调用对象不同
//		fromChannel.transferTo(0, toChannel.size(), toChannel);
		
	}
	
	
}
