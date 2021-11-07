package com.example.job_schduler.controller;

import com.example.job_schduler.service.QuartzManagerService;
import com.example.job_schduler.util.StringUtil;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.job_schduler.job.VoiceDisplayJob;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
public class JobController {
    @Autowired
    private QuartzManagerService jobManager;

    @PostMapping("/job_schduler")
    public String jobSchdulerMethod(@RequestBody String param) throws ParseException, SchedulerException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strStartTime = StringUtil.paramFromURLStringByName(param, "start_time");
        String strEndTime = StringUtil.paramFromURLStringByName(param, "end_time");
        Date startTime = simpleDateFormat.parse(strStartTime);
        Date endTime = simpleDateFormat.parse(strEndTime);

        jobManager.addJob("jobname-1", "jobgrouop-1", "trigger-1", "triggergroup-1",
                VoiceDisplayJob.class, "* * * * * ?", startTime, endTime);

        return param.toString();
    }

    @PostMapping("/job_delete")
    public void jobSchdulerDelete() throws SchedulerException {
        jobManager.removeJob("jobname-1", "jobgrouop-1", "trigger-1", "triggergroup-1");
    }
}
