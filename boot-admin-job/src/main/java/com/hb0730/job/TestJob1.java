package com.hb0730.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 简单测试
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
@Slf4j
public class TestJob1 extends AbstractSkipableJob {
    @Override
    protected void doExecute(JobExecutionContext context) throws JobExecutionException {
        log.info("~~ demo simple job ~~~~,测试1");
    }
}
