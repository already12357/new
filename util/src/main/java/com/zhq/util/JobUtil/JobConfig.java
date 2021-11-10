package com.zhq.util.JobUtil;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 任务分发器的配置类,
 * 用于给容器中注入对应的对象
 */
@Configuration
public class JobConfig {
    @Bean
    public Scheduler scheduler() throws SchedulerException {
//        waitForJobsToCompleteOnShutdown
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();

        return StdSchedulerFactory.getDefaultScheduler();
    }
}
