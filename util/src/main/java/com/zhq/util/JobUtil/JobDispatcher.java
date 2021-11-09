package com.zhq.util.JobUtil;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

// 用于调度发配对应的任务
public class JobDispatcher {
    @Autowired
    private Scheduler scheduler;

    /**
     * 向调度器中, 添加一个任务
     * @param jobId 任务的编号
     * @param clazz 对应任务的类
     * @param args 启动任务所需的参数, 以 <参数名, 参数>
     */
    public void addJob(String jobId, Class<? extends Job> clazz, Map<String, Object>...args) {
        JobDetail jobDetail = JobBuilder.newJob(clazz).build();
    }

    public void suspendJob() {

    }

    public void removeJob() {

    }
}
