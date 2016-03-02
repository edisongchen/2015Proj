package com.proj.common.util;

import org.junit.Test;

/**
*	
*	dbm 转换为W 	
* @author: Administrator
* @version: 1.0, 2016年3月2日
*/
public class DbmConvert {

	private static float P_DB1=1.25f; //+1db=1.25
	private static float P_DB2=1.6f; //+2db=1.6
	private static float P_DB3=2f; //+3db=2
	private static float P_DB10=10f;//+10db=10
	
	private static float N_DB1=0.8f; //+1db=1.25
	private static float N_DB2=0.625f; //+2db=1.6
	private static float N_DB3=0.5f; //+3db=2
	private static float N_DB10=0.1F;//+10db=10
	
	
	
	
	/**
	* @param dbm
	* @return
	*/
	public static float dbmToWatts(int dbm){
		//需要将dbm 拆分成上面定义的常量表示
		
		return 0f;
	}
	
	@Test
	public void test(){
//		 1, 2, 3, 10
//		-1,-2,-3,-10
		int dbm = 32;//0-70
		if (dbm == 30) {
			System.out.println( "=1w");
		}
		if (dbm > 30) {
			int sub1 = dbm-30;
			if (sub1 < 30) {
				//是否小于10
				int sub2=sub1-10;
				if (sub2 > 0) {
					
				}else if(sub2 < 0){
					//dBm =30dBm + sub2(dB)
					//test 3
					if (sub2 < 3) {
						
					}
					int mod = sub2 % 3;
				}else {
					System.out.println("-->30dbm+10db=10w");
				}
			} else if (sub1 >30){
				
			}else {
				System.out.println("=1000w");
			}
		}
	}
	
	
	public static void modeTest(int dbm){
//		int dbm = 33;//0-70
		//-10,-3,-2,-1,1,2,3,10
		int[] array = new int[]{0,0,0,0,0,0,0,0};
		if (dbm>30) {
			int sub1= dbm-30;
			int mod =sub1%10;
			if (mod == 0) {
				int counts = sub1/10;
				array[7]=array[7]+counts;
			}else if (sub1<=10) {
				//31-40
				process(array,dbm-30);
			}else if(sub1 <=20){
				//41-50
				array[7]=array[7]+1;
				process(array, dbm-40);
			} else if (sub1 <= 30){
				//51-60
				array[7]=array[7]+2;
				process(array, dbm-50);
			} else if (sub1 <= 40){
				//61-70
				array[7]=array[7]+3;
				process(array, dbm-60);
			}
		} else if(dbm < 30){
			
		}else {
			System.out.println("30dmb = 1w");
		}
	}
	
	public static void process(int[] array ,int dbm){
		//dbm 1-10 
		if ( dbm %3 == 0) {//3
			array[6]=array[6]+(dbm/3);
		}else if(dbm < 3){
			if (dbm==1) {//1
				array[4]=array[4]+1;
			}else {//2
				array[5]=array[5]+1;
			}
		}else if(dbm< 6){
			if (dbm == 4) {//4=1+3
				array[4]=array[4]+1;
				array[6]=array[6]+(dbm/3);
			}else {//5 3+2
				array[6]=array[6]+(dbm/3);
				array[5]=array[5]+1;
			}
		}else if(dbm < 9){
			if (dbm == 7) {//7 = 3+3+1
				array[6]=array[6]+2;
				array[4]=array[4]+1;
			}else {//8 = 3+3+2
				array[6]=array[6]+2;
				array[5]=array[5]+1;
			}
		}else {//10
			array[7]=array[7]+1;
		}
		int seq[]=new int[]{-10,-3,-2,-1,1,2,3,10};
		for(int i=0;i<8;i++){
			System.out.println(seq[i]+":"+array[i]);
		}
	}
	@Test
	public void tes2t (){
//		System.out.println(2%10);
		int array[] = new int[]{0,0,0,0,0,0,0,0}; 
//		process(array, 10);
		modeTest(42);
	}
}
