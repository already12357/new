package com.zhq;

import com.zhq.util.JobUtil.JobDispatcher;
import org.junit.Test;
import org.quartz.SchedulerException;

import java.util.Date;

public class JobDispatcherTest {
    @Test
    public void testJobDispatcher() throws SchedulerException {
        JobDispatcher jobDispatcher = new JobDispatcher();
        Date startDate = new Date();
        Date endDate = new Date(new Date().getTime() + 1000000000);
    }
}
