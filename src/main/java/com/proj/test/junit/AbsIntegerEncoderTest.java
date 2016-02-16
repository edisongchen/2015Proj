/**
 * 
 */
package com.proj.test.junit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author ctg
 * @date 2016年2月16日
 */
public class AbsIntegerEncoderTest {

	@Test
	public void testEncoded() {
		ByteBuf buf = Unpooled.buffer();
		for(int i=1;i<10;i++){
			buf.writeInt(i*(-1));
		}
		
		EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());
		Assert.assertTrue(channel.writeInbound(buf));
		
		Assert.assertTrue(channel.finish());
		for(int i=1;i<10;i++){
			Assert.assertEquals(i, channel.readInbound());
		}
		
		Assert.assertNull(channel.readInbound());
	}
	
}
