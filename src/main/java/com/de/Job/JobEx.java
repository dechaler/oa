package com.de.Job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @编写人:de
 * @时间:2019/10/19
 * @描述:
 */
@Component
public class JobEx implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("鸡儿棒y");
    }
}
