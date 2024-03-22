package com.hb0730.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
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
    protected void doExecute(JobExecutionContext context) throws JobExecutionException {
        log.info("~~ demo simple job ~~~~");
    }
}
