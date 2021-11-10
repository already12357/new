package com.epoint.pa.videonotice.job;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

// 用于调度发配对应的任务
@Component
public class JobDispatcher {
    public static final String TRIGGER_PREFIX = "trigger_";
    public static final String JOB_PREFIX = "job_";

    // 需要注入默认的调度器
    @Autowired
    private Scheduler scheduler;

    /**
     * 向调度器中, 添加一个任务, 默认状态下, 调度器时没有运行的
     * @param Id 用于定位任务的编号
     * @param startDate 任务的起始日期
     * @param endDate 任务的结束日期
     * @param minutesInterval 执行的时间间隔 ( 分钟 )
     * @param clazz 对应任务的类
     * @param args 启动任务所需的参数, 以 <参数名, 参数>
     */
    public void addJob(String Id, Date startDate, Date endDate, int minutesInterval,
                       Class<? extends Job> clazz, Map<String, Object> args)
            throws SchedulerException {
        // 通过 ID 拼接前缀来形成对应的编号
        String jobId = JOB_PREFIX + Id;
        String triggerId = TRIGGER_PREFIX + Id;
        JobKey jobKey = new JobKey(jobId);

        if (scheduler.checkExists(jobKey)) {
            scheduler.deleteJob(jobKey);
            jobKey = new JobKey(jobId);
        }

        JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobId).build();

        // 向工作的空间中传入参数
        if (null != args && !args.isEmpty()) {
            jobDetail.getJobDataMap().putAll(args);
        }

        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity(triggerId)
                .startNow()
                .endAt(endDate)
                .withSchedule(simpleScheduleBuilder.
                        withIntervalInMinutes(minutesInterval).
                        repeatForever()).build();

        scheduler.scheduleJob(jobDetail, simpleTrigger);
        scheduler.start();
    }

    public void suspendJob(String Id)
            throws SchedulerException {
        String jobId = JOB_PREFIX + Id;
        JobKey jobKey = JobKey.jobKey(jobId);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);

        String triggerId = TRIGGER_PREFIX + Id;
        TriggerKey triggerKey = new TriggerKey(triggerId);

        if (null != jobDetail) {
//            scheduler.pauseJob(jobKey);
            scheduler.pauseTrigger(triggerKey);
        }
    }

    public void resumeJob(String Id)
            throws SchedulerException {
        String jobId = JOB_PREFIX + Id;
        JobKey jobKey = JobKey.jobKey(jobId);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);

        String triggerId = TRIGGER_PREFIX + Id;
        TriggerKey triggerKey = new TriggerKey(triggerId);

        if (null != jobDetail) {
            scheduler.resumeTrigger(triggerKey);
        }
    }

    public void modifyJob(String Id, Date startDate, Date endDate, int minutesInterval) {
        String jobId = JOB_PREFIX + Id;
        String triggerId = TRIGGER_PREFIX + Id;
        JobKey jobKey = JobKey.jobKey(jobId);

        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity(triggerId)
                .startAt(startDate)
                .endAt(endDate)
                .withSchedule(simpleScheduleBuilder.withIntervalInMinutes(minutesInterval)).build();
    }


    public void clearAllJob() throws SchedulerException {
        scheduler.standby();
        scheduler.clear();
    }
}
