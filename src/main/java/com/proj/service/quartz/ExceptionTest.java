package com.proj.service.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.cronSchedule;
/**
 *
 * quartz异常处理
 * 
 * @author: Administrator
 * @version: 1.0, 2015年12月8日
 */
public class ExceptionTest {
	public static void main(String[] args) {
		try {
			SchedulerFactory sf = new StdSchedulerFactory();    
			Scheduler sched = sf.getScheduler();
			
			JobDetail job1 = newJob(BadJob1.class)    
				    .withIdentity("badJob1", "group1")    
				    .build();    
			CronTrigger trigger1 =newTrigger()
					.withIdentity("trigger1", "group1")
					.withSchedule(cronSchedule("0/10 * * * * ?"))
					.build();
			sched.scheduleJob(job1, trigger1);
		} catch (SchedulerException e) {
			System.out.println(e.getMessage()+"///////////////////");
		}
	}
}
