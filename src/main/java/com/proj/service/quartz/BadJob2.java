package com.proj.service.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
* it will not process exception 
* if it occur exception ,and when if
* it will fire the schedual
*		
* @author: Administrator
* @version: 1.0, 2015年12月8日
*/
public class BadJob2 implements Job{

	@Override
	public void execute(JobExecutionContext content) throws JobExecutionException {
		try {
			System.out.println("BadJob2.start");
			int zero = 0;
			int calcaute = 111/zero;
			System.out.println("BadJob2.end");
		} catch (Exception e) {
			JobExecutionException ex2 =  new JobExecutionException(e);
			ex2.setUnscheduleAllTriggers(true);
			throw ex2;
		}
	}

}
