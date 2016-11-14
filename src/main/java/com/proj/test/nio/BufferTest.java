/**
 * 
 */
package com.proj.test.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.junit.Test;

/**
 * JAVA NIO 中Buffer 与Channel交互
 * 数据是从通道读入缓冲区，从缓冲区写入到通道中的
 * Buffer本质是一块可读写的内存,被封装成对象.本质 postion,mark,limit,capacity的几个指针改变
 * 
 * rewind
 * buf.get(..)
 * buf.put(..)
 * clear()
 * compact()
 * mark reset
 * @author ctg
 * @date 2016年1月27日
 */
public class BufferTest {
/**
 * 通过allocate 指定固定大小的缓冲区，称之为 分配，创建一个缓冲区对象并分配一个私有的空间来
 * 存储容量大小的数据元素。
 * 通过wrap 称为包装操作来创建一个缓冲区对象，但是不分配任何空间来存储数据元素，它
 * 使用你所提供的数组作为存储空间来存储缓冲区中的数据
 * 
 * 通过allocate或者wrap函数创建的缓冲区通常都是间接地。(堆)
 * hasArray 返回true(堆中分配,由gc可负责回收) 告诉你这个缓冲区是一个可存取的备份数组，array()方法将返回
 * 这个缓冲区对象所使用的数组存储空间的引用。
 * 
*
 */
	@Test
	public void BaseMethod(){
		//1,写入数据到buffer
		//2,调用flip方法  写模式切换到读，相应指针改变
		//3,从buffer中读取数据
		//4,调用clear方法清空整个缓冲区，或compact方法清空已读数据,未读数据转移到缓冲区起始处  
		byte b1 =3;
		byte b2 =2;
		System.out.println(b1 & b2);
	}
	@Test
	public void get(){
		ByteBuffer buffer = ByteBuffer.allocate(10);
		byte[] bytes = new byte[10];
		int i=0;
		while(i<10){
			bytes[i]=(byte)i;
			i++;
		}
		buffer.put(bytes);
		buffer.flip();//翻转
		byte[] dst = new byte[4];
		//buffer.get(dst); //如果目标数组大于缓冲区声明的空间大小，有异常
		//要将缓冲区释放到一个大数组中，需要确定缓冲区中的数据能全部
		//填满数组，否者抛出异常BufferUnderflowException
		
		//正确的步奏是
		//int length = buffer.remaining();
		//buffer.get(dst,0,length);
		
		//如果缓冲区中的数据，多于数组大小，使用如下
		while(buffer.hasRemaining()){
			int leng = Math.min(buffer.remaining(), dst.length);
			buffer.get(dst, 0, leng);
			for(int k=0;k<leng;k++){
				System.out.println(dst[k]);
			}
			System.out.println("///////");
		}
	}
	
	@Test
	public void wrap(){
		//wrap函数创建一个只读的备份存储区是CharSequence接口或者其实现的缓冲区对象
		//Charsequence描述了一个可读的字符流。3个标准的实现了该接口的:String,StringBuffer,
		//CharBuffer
		CharBuffer buffer = CharBuffer.wrap("helloadmin");
		while(buffer.hasRemaining()){
			System.out.println(buffer.get());
		}
	}
}
