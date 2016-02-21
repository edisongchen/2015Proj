package com.proj.test.netty.codec;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WindowsTime {
	private long now;
	private String hhmm;
	
	public WindowsTime() {
		super();
		now = System.currentTimeMillis();
		hhmm = new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date());
	}
	public long getNow() {
		return now;
	}
	public void setNow(long pNow) {
		now = pNow;
	}
	public String getHhmm() {
		return hhmm;
	}
	public void setHhmm(String pHhmm) {
		hhmm = pHhmm;
	}
	
	
}
