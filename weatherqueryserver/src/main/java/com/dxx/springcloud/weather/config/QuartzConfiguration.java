package com.dxx.springcloud.weather.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dxx.springcloud.weather.job.WeatherDataSyncJob;

@Configuration
public class QuartzConfiguration {
	private final int TIME = 180;
	@Bean
	public JobDetail weatherDataSyncJobJobDetail() {
		return JobBuilder
				.newJob(WeatherDataSyncJob.class)
				.withIdentity("WeatherDataSyncJob")
				.storeDurably()
				.build();
	}
	
	@Bean
	public Trigger sampleJobTrigger(JobDetail jobDetail) {
		SimpleScheduleBuilder scheduleBuilder = 
				SimpleScheduleBuilder
				.simpleSchedule()
				.withIntervalInSeconds(TIME)
				.repeatForever();
		
		return TriggerBuilder
				.newTrigger()
				.forJob(jobDetail)
				.withIdentity("weatherDataSyncTrigger")
				.withSchedule(scheduleBuilder)
				.build();
		
	}
}
