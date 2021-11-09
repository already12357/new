package com.example.job_schduler.service;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class QuartzManagerService {
    @Autowired
    private Scheduler scheduler;

    public void addJob(String jobName, String jobGroupName, String triggerName,
                       String triggerGroupName, Class jobClass, String cron, Date startTime, Date endTime) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();

        // 给任务上下文传递参数
        jobDetail.getJobDataMap().put("startTime", startTime);
        jobDetail.getJobDataMap().put("endTime", endTime);
        jobDetail.getJobDataMap().put("cron", cron);
//        if (contextParams != null) {
//            // 使用 .getJobDetail().getJobDataMap(); 向上下文传递参数
//            for (int i = 0; i < contextParams.length; i++) {
//                jobDetail.getJobDataMap().put("data", contextParams[i]);
//            }
//        }

        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
        triggerBuilder.withIdentity(triggerName, triggerGroupName);
        // 设置触发器的时间
        triggerBuilder.startAt(startTime);
        triggerBuilder.endAt(endTime);
        // 触发器的内部运行时间
        triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));

        CronTrigger trigger = (CronTrigger) triggerBuilder.build();
        // 将对应的任务设定为对应的触发器触发
        scheduler.scheduleJob(jobDetail, trigger);

        if (!scheduler.isShutdown()) {
            // 启动对应的任务
            scheduler.start();
        }
    }

    public void removeJob(String jobName, String jobGroupName, String triggerName,
                          String triggerGroupName) throws SchedulerException {
        // 获取到对应的触发器
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        // 暂停触发器的执行
        scheduler.pauseTrigger(triggerKey);
        // 删除对应的触发器里执行的任务
        scheduler.unscheduleJob(triggerKey);
        scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));

        System.out.println("remove : " + JobKey.jobKey(jobName));
    }
}
