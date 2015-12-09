package com.proj.service.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class ModifyJobTest {
	public static void main(String[] args) {
		Scheduler scheduler;
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			QuartzUtil.createJob(scheduler, "job1", "group1",
					"0/2 * * * * ?", null, SimpleJob.class);
			
			QuartzUtil.startUp(scheduler);
			Thread.sleep(5000);
			QuartzUtil.pauseJob(scheduler, "job1", "group1");
			Thread.sleep(5000);
			QuartzUtil.resumeJob(scheduler, "job1", "group1");
			Thread.sleep(6000);
			QuartzUtil.deleteScheduleJob(scheduler, "job1", "group1");
			System.out.println("/////////////");
//			QuartzUtil.updateScheduleJob(scheduler, "job1", "group1",
//					"0/5 * * * * ?", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
