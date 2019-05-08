package com.minude.demo.quartz.web;

import com.github.pagehelper.PageInfo;
import com.minude.demo.quartz.JobServiceImpl;
import com.minude.demo.quartz.entity.QuartzJob;
import com.minude.demo.quartz.web.common.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author minude
 * @Date 2019/5/7 14:38
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/job")
public class JobController {

    private JobServiceImpl jobService;

    @Autowired
    public void setJobService(JobServiceImpl jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/add")
    public RestResult save(QuartzJob quartz) {
        log.info("新增任务");
        RestResult result = jobService.saveJob(quartz);
        return result;
    }

    @GetMapping("/list")
    public RestResult list(String jobName, Integer pageNum, Integer pageSize) {
        log.info("任务列表");
        PageInfo<QuartzJob> pageInfo = jobService.listQuartzJob(jobName, pageNum, pageSize);
        return RestResult.success(pageInfo);
    }

    @PostMapping("/trigger")
    public RestResult trigger(String jobName, String jobGroup) {
        log.info("触发任务");
        RestResult result = jobService.triggerJob(jobName, jobGroup);
        return result;
    }

    @PostMapping("/pause")
    public RestResult pause(String jobName, String jobGroup) {
        log.info("停止任务");
        RestResult result = jobService.pauseJob(jobName, jobGroup);
        return result;
    }

    @PostMapping("/resume")
    public RestResult resume(String jobName, String jobGroup) {
        log.info("恢复任务");
        RestResult result = jobService.resumeJob(jobName, jobGroup);
        return result;
    }

    @PostMapping("/remove")
    public RestResult remove(String jobName, String jobGroup) {
        log.info("移除任务");
        RestResult result = jobService.removeJob(jobName, jobGroup);
        return result;
    }
}
