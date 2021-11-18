package com.zhq.util.JobUtil.util3;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

// Quartz 的配置类
// 不使用 Quartz Springboot 启动环境时，手动配置，将对应生成的任务注入到 Spring 中，
// 交由 IOC 管理
@Configuration
public class QuartzConfig {
    @Autowired
    private JobFactory jobFactory;

    /**
     * 有编写配置文件时, 注入对应的 SchedulerFactoryBean
     * 用于生成对应的 Scheduler
     * @return
     */
    @Bean
    public SchedulerFactoryBean schedulerFactory() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(jobFactory);
        schedulerFactoryBean.setConfigLocation(new ClassPathResource("quartz.properties"));
        return schedulerFactoryBean;
    }


    /**
     * 当没有编写对应的 .properties 文件，注入非持久化的 Scheduler
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public Scheduler ramScheduler()
            throws SchedulerException {
        return StdSchedulerFactory.getDefaultScheduler();
    }
}
