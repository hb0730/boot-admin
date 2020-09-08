package com.hb0730.boot.admin.task.quartz.utils;

import com.hb0730.boot.admin.task.domain.JobInfo;
import com.hb0730.boot.admin.task.quartz.constant.ScheduleConstants;
import com.hb0730.boot.admin.task.quartz.job.QuartzDisallowConcurrentExecution;
import org.quartz.*;

/**
 * 定时任务工具类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class ScheduleUtils {
    /**
     * 构建任务触发对象
     *
     * @param jobId    任务id
     * @param jobGroup 任务组
     */
    public static TriggerKey getTriggerKey(Long jobId, String jobGroup) {
        return TriggerKey.triggerKey(ScheduleConstants.TASK_CLASS_NAME + jobId, jobGroup);
    }

    /**
     * 构建任务键对象
     *
     * @param jobId    任务id
     * @param jobGroup 任务组
     */
    public static JobKey getJobKey(Long jobId, String jobGroup) {
        return JobKey.jobKey(ScheduleConstants.TASK_CLASS_NAME + jobId, jobGroup);
    }

    /**
     * 创建定时任务
     *
     * @param job       任务信息
     * @param scheduler 任务调度{@link Scheduler}
     * @throws SchedulerException 任务判断异常
     */
    public static void createScheduleJob(Scheduler scheduler, JobInfo job) throws SchedulerException {
        Class<? extends Job> quartzJobClass = getQuartzJobClass(job);
        // 构建job信息
        Long jobId = job.getId();
        String jobGroup = job.getGroup();
        JobDetail jobDetail = JobBuilder.newJob(quartzJobClass).withIdentity(getJobKey(jobId, jobGroup)).build();

        // 表达式调度构建器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());

        // 按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(jobId, jobGroup))
                .withSchedule(cronScheduleBuilder).build();

        // 放入参数，运行时的方法可以获取
        jobDetail.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES, job);
        // 判断是否存在
        if (scheduler.checkExists(getJobKey(jobId, jobGroup))) {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(getJobKey(jobId, jobGroup));
        }
        scheduler.scheduleJob(jobDetail, trigger);

        // 暂停任务
        if (ScheduleConstants.Status.PAUSE.getValue().equals(job.getIsEnabled())) {
            scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
    }

    /**
     * 得到quartz任务类
     *
     * @param job 执行计划
     * @return 具体执行任务类
     */
    private static Class<? extends Job> getQuartzJobClass(JobInfo job) {
        // 非并发
        return QuartzDisallowConcurrentExecution.class;
    }
}
