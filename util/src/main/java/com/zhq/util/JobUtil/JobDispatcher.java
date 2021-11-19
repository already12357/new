package com.zhq.util.JobUtil;

import com.aspose.cad.internal.F.P;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// 用于调度发配对应的任务
// 默认注入 IOC
@Component
public class JobDispatcher {
    public static final String TRIGGER_PREFIX = "trigger_";
    public static final String JOB_PREFIX = "job_";

    // 需要注入默认的调度器
    @Autowired
    private Scheduler scheduler;

    /**
     * 向调度器中, 添加一个任务, 默认状态下, 调度器时没有运行的
     * @param id 用于定位任务的编号
     * @param startDate 任务的起始日期
     * @param endDate 任务的结束日期
     * @param intervalTimeUnit 执行时间间隔的颗粒度
     * @param interval 执行的时间间隔 ( 分钟 )
     * @param clazz 对应任务的类
     * @param args 启动任务所需的参数, 以 <参数名, 参数>
     */
    public void addJob(String id, Date startDate, Date endDate, TimeUnit intervalTimeUnit, int interval,
                       Class<? extends Job> clazz, Map<String, Object> args) {
        // 通过 ID 拼接前缀来形成对应的编号
        String jobId = JOB_PREFIX + id;
        String triggerId = TRIGGER_PREFIX + id;
        JobKey jobKey = new JobKey(jobId);
        TriggerKey triggerKey = new TriggerKey(triggerId);

        try {
            if (scheduler.checkExists(jobKey)) {
                scheduler.deleteJob(jobKey);
                jobKey = new JobKey(jobId);
            }

            // 建立对应的任务对象
            // 注意设置其 duration 属性为 true, 保证在对应的触发器删除时, 任务不不删除
            JobDetail jobDetail = JobBuilder.newJob(clazz).
                    withIdentity(jobId).
                    storeDurably(true).build();

            // 向工作的空间中传入参数
            if (null != args && !args.isEmpty()) {
                jobDetail.getJobDataMap().putAll(args);
            }

            SimpleTrigger simpleTrigger =  simpleTriggerWithTimeUnit(jobDetail, triggerKey, startDate, endDate, intervalTimeUnit, interval);

            scheduler.scheduleJob(jobDetail, simpleTrigger);
            scheduler.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停一个任务, 同时暂停其触发器
     * @param id 任务的 Id
     */
    public void suspendJob(String id) {
        String jobId = JOB_PREFIX + id;
        JobKey jobKey = JobKey.jobKey(jobId);
        String triggerId = TRIGGER_PREFIX + id;
        TriggerKey triggerKey = new TriggerKey(triggerId);
        JobDetail jobDetail = null;

        try {
            jobDetail = scheduler.getJobDetail(jobKey);

            if (null != jobDetail) {
                scheduler.pauseTrigger(triggerKey);
                scheduler.pauseJob(jobKey);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 恢复一个任务
     * @param id 恢复任务编号
     * @throws SchedulerException
     */
    public void resumeJob(String id) {
        String jobId = JOB_PREFIX + id;
        JobKey jobKey = JobKey.jobKey(jobId);
        String triggerId = TRIGGER_PREFIX + id;
        TriggerKey triggerKey = new TriggerKey(triggerId);
        JobDetail jobDetail = null;

        try {
            jobDetail = scheduler.getJobDetail(jobKey);

            if (null != jobDetail) {
                scheduler.resumeTrigger(triggerKey);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 任务执行的调整
     * @param id 任务编号
     * @param startDate 起始日期
     * @param endDate 结束日期
     * @param intervalTimeUnit 时间间隔的颗粒度
     * @param interval 间隔大小
     */
    public void modifyJob(String id, Date startDate, Date endDate, TimeUnit intervalTimeUnit, int interval) {
        String jobId = JOB_PREFIX + id;
        JobKey jobKey = JobKey.jobKey(jobId);
        String triggerId = TRIGGER_PREFIX + id;
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerId);
        JobDetail jobDetail = null;

        try {
            jobDetail = scheduler.getJobDetail(jobKey);

            // 停止触发器的触发
            // 停止当前任务的执行
            scheduler.pauseTrigger(triggerKey);
            scheduler.pauseJob(jobKey);
            scheduler.unscheduleJob(triggerKey);

            SimpleTrigger simpleTrigger = simpleTriggerWithTimeUnit(jobDetail, triggerKey, startDate, endDate, intervalTimeUnit, interval);

            // 重新配置任务对应的执行器,并启动执行
            scheduler.scheduleJob(simpleTrigger);
            scheduler.resumeJob(jobKey);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 清楚一个调度器下的所有任务和调度器
     */
    public void clearAllJob() {
        try {
            scheduler.standby();
            scheduler.clear();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



    public SimpleTrigger simpleTriggerWithTimeUnit(JobDetail jobDetail, TriggerKey triggerKey, Date startDate, Date endDate, TimeUnit intervalUnit, int interval) {
        SimpleTrigger simpleTrigger = null;
        SimpleScheduleBuilder simpleScheduleBuilder = null;

        try {
            simpleScheduleBuilder = simpleScheduleBuilderWithTimeUnit(intervalUnit, interval);
            simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .startAt(startDate)
                    .endAt(endDate)
                    .forJob(jobDetail)
                    .withSchedule(simpleScheduleBuilder)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return simpleTrigger;
    }

    public SimpleScheduleBuilder simpleScheduleBuilderWithTimeUnit(TimeUnit intervalUnit, int interval) {
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .repeatForever();

        switch (intervalUnit) {
            case SECONDS:
                simpleScheduleBuilder.withIntervalInSeconds(interval);
                break;
            case MINUTES:
                simpleScheduleBuilder.withIntervalInMinutes(interval);
                break;
            case HOURS:
                simpleScheduleBuilder.withIntervalInHours(interval);
                break;
            case MICROSECONDS:
                simpleScheduleBuilder.withIntervalInMilliseconds(interval);
                break;
        }

        return simpleScheduleBuilder;
    }
}