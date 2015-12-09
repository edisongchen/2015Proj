package com.proj.service.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
* it will generate a exception and 
* force to refire
*		
* @author: Administrator
* @version: 1.0, 2015年12月8日
*/
public class BadJob1 implements Job{

	@Override
	public void execute(JobExecutionContext content) throws JobExecutionException {
		try {
			System.out.println("BadJob1.start");
			int zero = 0;
			int calcaute = 111/zero;
			System.out.println("BadJob1.end");
		} catch (Exception e) {
			JobExecutionException ex2 =  new JobExecutionException(e);
			ex2.refireImmediately();
			throw ex2;
		}
		
	}

}
