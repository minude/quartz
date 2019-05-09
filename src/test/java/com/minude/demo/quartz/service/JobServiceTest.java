package com.minude.demo.quartz.service;

import com.minude.demo.quartz.entity.QuartzJob;
import com.minude.demo.quartz.task.HelloTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author minude
 * @Date 2019/5/7 15:22
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobServiceTest {

    private JobServiceImpl jobService;

    @Autowired
    public void setJobService(JobServiceImpl jobService) {
        this.jobService = jobService;
    }

    @Test
    public void addJob() {

        String jobName = "test-job";
        String jobGroup = "1";
        String description = "desc";
        String jobClassName = HelloTask.class.getName();
        String corn = "*/5 * * * * ?";
        QuartzJob job = new QuartzJob(jobName, jobGroup, description, jobClassName, corn);
        jobService.saveJob(job);
    }

}
