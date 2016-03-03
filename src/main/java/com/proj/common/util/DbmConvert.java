package com.proj.common.util;

import org.junit.Test;

/**
*	
*	dbm 转换为W 	
* @author: Administrator
* @version: 1.0, 2016年3月2日
*/
public class DbmConvert {

	@Test
	public void WattsToDbm(){
		//dbm = 10lg(w/mw)
//		W 转 dbm
		System.out.println(10*(Math.log(2000)/Math.log(10)));
	}
	
	@Test
	public void dbmToWatts(){
		float dbm = 56f;
		float a =1/10f;
//		System.out.println(Math.pow(10, (dbm/10 -3)));
		System.out.println(dbm/a);
	}
	
	
}
