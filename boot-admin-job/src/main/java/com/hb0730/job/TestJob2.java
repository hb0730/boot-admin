package com.hb0730.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 参数测试
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
@Slf4j
public class TestJob2 extends AbstractSkipableJob {
    @Override
    protected void doExecute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        Object parameter = jobDataMap.get("parameter");
        log.info("~~ demo simple job ~~~~,测试2,参数：{}", parameter);
    }
}
