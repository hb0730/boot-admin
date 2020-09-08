package com.hb0730.boot.admin.task.handler;

import com.hb0730.boot.admin.task.domain.JobInfo;
import com.hb0730.boot.admin.task.quartz.constant.ScheduleConstants;
import com.hb0730.boot.admin.task.quartz.utils.ScheduleUtils;
import lombok.RequiredArgsConstructor;
import org.quartz.JobDataMap;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;

/**
 * job 帮助类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Component
@RequiredArgsConstructor
public class JobHelper {
    private final SchedulerFactoryBean factoryBean;

    /**
     * 检查任务是否存在
     *
     * @param jobId    任务id
     * @param jobGroup 任务分组
     * @return 是否存在
     */
    public boolean checkExists(@Nonnull Long jobId, @Nonnull String jobGroup) throws SchedulerException {
        Assert.notNull(jobId, "job id 不为空");
        Assert.notNull(jobGroup, "job group 不为空");
        Scheduler scheduler = getScheduler();
        return scheduler.checkExists(ScheduleUtils.getJobKey(jobId, jobGroup));
    }

    /**
     * 暂停任务
     *
     * @param jobId    任务id
     * @param jobGroup 任务分组
     */
    public void pauseJob(@Nonnull Long jobId, @Nonnull String jobGroup) throws SchedulerException {
        Assert.notNull(jobId, "job id 不为空");
        Assert.notNull(jobGroup, "job group 不为空");
        Scheduler scheduler = getScheduler();
        scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
    }

    /**
     * 恢复任务
     *
     * @param jobId    任务id
     * @param jobGroup 任务分组
     */
    public void resumeJob(@Nonnull Long jobId, @Nonnull String jobGroup) throws SchedulerException {
        Assert.notNull(jobId, "job id 不为空");
        Assert.notNull(jobGroup, "job group 不为空");
        Scheduler scheduler = getScheduler();
        scheduler.resumeJob(ScheduleUtils.getJobKey(jobId, jobGroup));
    }

    /**
     * 删除任务
     *
     * @param jobId    任务id
     * @param jobGroup 任务分组
     */
    public void deleteJob(@Nonnull Long jobId, @Nonnull String jobGroup) throws SchedulerException {
        Assert.notNull(jobId, "job id 不为空");
        Assert.notNull(jobGroup, "job group 不为空");
        Scheduler scheduler = getScheduler();
        scheduler.deleteJob(ScheduleUtils.getJobKey(jobId, jobGroup));
    }

    /**
     * 立即运行
     *
     * @param jobId    任务id
     * @param jobGroup 任务分组
     * @param info     job 参数
     */
    public void runJob(@Nonnull Long jobId, @Nonnull String jobGroup, @Nonnull JobInfo info) throws SchedulerException {
        Assert.notNull(jobId, "job id 不为空");
        Assert.notNull(jobGroup, "job group 不为空");
        Assert.notNull(info, "job info 不为空");
        // 参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES, info);
        Scheduler scheduler = getScheduler();
        scheduler.triggerJob(ScheduleUtils.getJobKey(jobId, jobGroup), dataMap);
    }

    /**
     * 新增job
     *
     * @param job job
     */
    public void addJob(@Nonnull JobInfo job) throws SchedulerException {
        Assert.notNull(job, "job info 不为空");
        Scheduler scheduler = getScheduler();
        ScheduleUtils.createScheduleJob(scheduler, job);
    }

    private Scheduler getScheduler() {
        return factoryBean.getScheduler();
    }
}
