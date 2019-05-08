package com.minude.demo.quartz.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author minude on 2019/5/7 14:53
 */
@Data
@NoArgsConstructor
public class QuartzJob {

    private String jobName;
    private String jobGroup;
    private String description;
    private String jobClassName;
    private String cronExpression;
    private String triggerName;
    private String triggerState;
    private List<Map<String, Object>> jobDataParam;

    public QuartzJob(String jobName, String jobGroup, String description, String jobClassName, String cronExpression) {

        super();
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.description = description;
        this.jobClassName = jobClassName;
        this.cronExpression = cronExpression;
    }
}

