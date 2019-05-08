package com.minude.demo.quartz.dao;

import com.minude.demo.quartz.entity.QuartzJob;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author minude on 2019/5/7 15:13
 */
@Repository
public interface QuartzJobMapper {

    /**
     * 查询定时任务
     *
     * @param jobName 任务名,为空时查全部
     * @return List<QuartzJob>
     */
    List<QuartzJob> list(String jobName);
}
