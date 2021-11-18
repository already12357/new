package com.zhq.util.JobUtil.util2;

import org.springframework.beans.factory.annotation.Autowired;
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
     * 注入调度器生产工厂
     * @return
     */
    @Bean
    public SchedulerFactoryBean schedulerFactory() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(jobFactory);
        schedulerFactoryBean.setConfigLocation(new ClassPathResource("./config/quartz.properties"));
        return schedulerFactoryBean;
    }
}
