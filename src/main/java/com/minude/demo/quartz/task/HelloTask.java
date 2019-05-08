package com.minude.demo.quartz.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Author minude
 * @Date 2019/5/7 11:24
 * @Version 1.0
 */
public class HelloTask implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("hello world");
    }
}
