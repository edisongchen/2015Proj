/**
 * 
 */
package com.proj.test.nio;

import org.junit.Test;

/**
 * JAVA NIO 中Buffer 与Channel交互
 * 数据是从通道读入缓冲区，从缓冲区写入到通道中的
 * Buffer本质是一块可读写的内存,被封装成对象.本质 postion,limit,capacity的几个指针改变
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
}
