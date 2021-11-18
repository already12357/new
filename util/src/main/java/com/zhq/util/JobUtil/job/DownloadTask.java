package com.zhq.util.JobUtil.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@DisallowConcurrentExecution
public class DownloadTask extends QuartzJobBean {
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println(new Date() + "任务开始------------------------------------");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + "任务结束------------------------------------");
    }
}
