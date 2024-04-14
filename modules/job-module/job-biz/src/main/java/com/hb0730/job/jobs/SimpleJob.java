package com.hb0730.job.jobs;

import com.hb0730.base.exception.JobException;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

/**
 * 简单的定时任务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/21
 */
@Component
@Slf4j
public class SimpleJob extends AbstractSkipableJob {
    @Override
    protected void doExecute(JobExecutionContext context) throws JobException {
        log.info("~~ demo simple job ~~~~");
    }
}