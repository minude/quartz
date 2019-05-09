package com.minude.demo.quartz.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minude.demo.quartz.dao.QuartzJobMapper;
import com.minude.demo.quartz.entity.QuartzJob;
import com.minude.demo.quartz.common.RestResult;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author minude on 2019/5/7 14:29
 */
@Service
public class JobServiceImpl {

    private Scheduler scheduler;

    @Autowired
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    private QuartzJobMapper mapper;

    @Autowired
    public void setMapper(QuartzJobMapper mapper) {
        this.mapper = mapper;
    }

    public PageInfo<QuartzJob> listQuartzJob(String jobName, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<QuartzJob> list = mapper.list(jobName);
        return new PageInfo<>(list);
    }

    public RestResult saveJob(QuartzJob quartz) {
        try {
            //构建job信息
            Class cls = Class.forName(quartz.getJobClassName());
            JobDetail job = JobBuilder.newJob(cls).withIdentity(quartz.getJobName(),
                    quartz.getJobGroup())
                    .withDescription(quartz.getDescription()).build();
            // 触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression().trim());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + quartz.getJobName(), quartz.getJobGroup())
                    .startNow().withSchedule(cronScheduleBuilder).build();
            //交由Scheduler安排触发
            scheduler.scheduleJob(job, trigger);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResult.sysErr();
        }
        return RestResult.success();
    }

    public RestResult triggerJob(String jobName, String jobGroup) {
        JobKey key = new JobKey(jobName, jobGroup);
        try {
            scheduler.triggerJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return RestResult.sysErr();
        }
        return RestResult.success();
    }

    public RestResult pauseJob(String jobName, String jobGroup) {
        JobKey key = new JobKey(jobName, jobGroup);
        try {
            scheduler.pauseJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return RestResult.sysErr();
        }
        return RestResult.success();
    }

    public RestResult resumeJob(String jobName, String jobGroup) {
        JobKey key = new JobKey(jobName, jobGroup);
        try {
            scheduler.resumeJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return RestResult.sysErr();
        }
        return RestResult.success();
    }

    public RestResult removeJob(String jobName, String jobGroup) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
            System.out.println("removeJob:" + JobKey.jobKey(jobName));
        } catch (Exception e) {
            e.printStackTrace();
            return RestResult.sysErr();
        }
        return RestResult.success();
    }
}