package com.proj.service.quartz;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.cronSchedule;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
* 测试类,函数式编程实现定时器
* 其中要解决静态引入问题(有时候不知道是哪个类里的)
*		
* @author: Administrator
* @version: 1.0, 2015年11月19日
*/
public class CronTriggerDemo {
	public static void main(String[] args) {
		SchedulerFactory sf = new StdSchedulerFactory();
		try {
			Scheduler sched = sf.getScheduler();
			//job1
			JobDetail job = newJob(SimpleJob.class)//JobBuilder.newJob(Class<? extends Job> jobClass)
					.withIdentity("job1","group1")//JobBuilder.withIdentity(String name, String group)
					.build();//return JobDetail
			CronTrigger trigger =newTrigger()
					.withIdentity("trigger1", "group1")
					.withSchedule(cronSchedule("0/10 * * * * ?"))
					.build();
			sched.scheduleJob(job, trigger);
			//job2
			job = newJob(SimpleJob.class)
					.withIdentity("job2", "group1")
					.build();
			trigger = newTrigger()
					.withIdentity("trigger2", "group1")
					.withSchedule(cronSchedule("0/15 * * * * ?"))
					.build();
			sched.scheduleJob(job, trigger);
			
			
			sched.start();
			Thread.sleep(30*1000L);
			sched.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
}
