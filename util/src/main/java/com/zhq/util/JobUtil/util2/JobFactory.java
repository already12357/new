package com.zhq.util.JobUtil.util2;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

@Component
public class JobFactory extends AdaptableJobFactory {
    // 注入对象，该对象用于将其他对象注入到 Spring IOC 中
    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        // 使用 AutowireCapableBeanFactory 将建立的实体类注入到 Spring 的 IOC 中
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
