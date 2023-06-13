package com.hb0730.boot.admin.job;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * 带参数的JOB
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/12
 */
@Slf4j
public class SampleParamJob implements Job {

    /**
     * 任务参数
     */
    private String parameter;

    /**
     * 设置任务参数
     *
     * @param parameter 任务参数
     */
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    //------------------------------------------------------------------------------------------------

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        log.info("welcome {}! 带参数定时任务 SampleParamJob ! 时间 :{}", this.parameter, DateUtil.now());
    }
}
