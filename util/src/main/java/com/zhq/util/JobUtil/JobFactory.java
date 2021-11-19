package com.zhq.util.JobUtil;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

@Component
public class JobFactory extends AdaptableJobFactory {
    // 注入对象，该对象用于将其他对象注入到 IOC 中
    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    /**
     * 每次在执行任务时，会由该方法创建对应的任务实例来执行，然后在执行完成后销毁
     * @param bundle
     * @return
     * @throws Exception
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle)
            throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        // 使用 AutowireCapableBeanFactory 将建立的实体类注入到 Spring 的 IOC 中,
        // 注入后，可以通过 Spring 对对应的任务进行操作
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
