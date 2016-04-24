package com.ichinaweb.listener;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.ichinaweb.job.TimerJob;

@Component
public class StartupListener implements ApplicationContextAware {
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
//		JobDetail jobDetail = JobBuilder.newJob(TimerJob.class).withIdentity("testJob", "jobGroup").build();
//		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("testTrigger", "triggerGroup").startNow().
//				withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever()).build();
//		SchedulerFactory sf = new StdSchedulerFactory();
//		Scheduler sched; 
//		try {
//			sched = sf.getScheduler();
//			sched.scheduleJob(jobDetail, trigger);
//			sched.start();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
	}

}

