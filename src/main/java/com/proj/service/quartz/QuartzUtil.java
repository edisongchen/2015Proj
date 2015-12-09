package com.proj.service.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

public class QuartzUtil {

	public static TriggerKey getTriggerKey(String jobName, String jobGroup) {
		return TriggerKey.triggerKey(jobName, jobGroup);
	}

	public static JobKey getJobKey(String jobName, String jobGroup) {

		return JobKey.jobKey(jobName, jobGroup);
	}


	public static CronTrigger getCronTrigger(Scheduler scheduler, String jobName, String jobGroup) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
			return (CronTrigger) scheduler.getTrigger(triggerKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	public static void createJob(Scheduler scheduler, String jobName, String jobGroup, String cronExpression,
			Object param, Class<? extends Job> jobClass) {
		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroup).build();
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup).withSchedule(scheduleBuilder)
				.build();
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static void updateScheduleJob(Scheduler scheduler, String jobName, String jobGroup, String cronExpression,
			Object param) {
		try {
			TriggerKey triggerKey = getTriggerKey(jobName, jobGroup);
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			scheduler.rescheduleJob(triggerKey, trigger);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static void startUp(Scheduler scheduler) {
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}



	/**
	 *
	 * 暂停
	 * 
	 * @param scheduler
	 * @param jobName
	 * @param jobGroup
	 */
	public static void pauseJob(Scheduler scheduler, String jobName, String jobGroup) {
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		try {
			scheduler.pauseJob(jobKey);
			System.out.println("pause//////////////");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * 恢复
	 * 
	 * @param scheduler
	 * @param jobName
	 * @param jobGroup
	 */
	public static void resumeJob(Scheduler scheduler, String jobName, String jobGroup) {

		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		try {
			scheduler.resumeJob(jobKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteScheduleJob(Scheduler scheduler, String jobName, String jobGroup) {
        try {
            scheduler.deleteJob(getJobKey(jobName, jobGroup));
            System.out.println("deleted....");
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}
