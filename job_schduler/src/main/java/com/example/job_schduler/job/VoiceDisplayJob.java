package com.example.job_schduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class VoiceDisplayJob extends QuartzJobBean {
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println(context.getJobDetail().getKey().getName());
        System.out.println(context.getJobDetail().getKey().getGroup());
        System.out.println("startTime : " + context.getJobDetail().getJobDataMap().get("startTime"));
        System.out.println("endTime : " + context.getJobDetail().getJobDataMap().get("endTime"));
        System.out.println("cron : " + context.getJobDetail().getJobDataMap().get("cron"));
    }
}
