package com.hb0730.jobs;

import com.hb0730.base.exception.JobException;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 抽象的可跳过的定时任务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/14
 */
@Slf4j
public abstract class AbstractSkipableJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        if (isSkip(context)) {
            log.info("~~~定时任务跳过本次执行~~~");
            return;
        }
        // 可进行try-catch处理，异步通知执行结果
        doExecute(context);
    }

    /**
     * 是否跳过
     *
     * @param context 作业执行上下文
     * @return true:跳过 false:不跳过
     */
    protected boolean isSkip(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        return jobDataMap.getBoolean("skip");
    }

    /**
     * 执行具体的任务
     *
     * @param context 作业执行上下文
     * @throws JobException 作业执行异常
     */
    protected abstract void doExecute(JobExecutionContext context) throws JobException;
}