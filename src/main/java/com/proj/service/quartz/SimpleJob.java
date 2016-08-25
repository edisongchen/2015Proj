package com.proj.service.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class SimpleJob implements Job{

	@Override
	public void execute(JobExecutionContext pJobexecutioncontext) throws JobExecutionException {
		JobKey jobKey = pJobexecutioncontext.getJobDetail().getKey();
		System.out.println("SimpleJob.getJobkey:"+jobKey);
	}

}
