package com.hb0730.service;

import com.hb0730.base.exception.JobException;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.rpc.job.domain.QuartzJobDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/14
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JobManager {
    private static final String JOB_NAME = "TASK_";
    private final Scheduler scheduler;
    private final BeanFactory beanFactory;

    /**
     * 添加定时任务
     *
     * @param quartzJob 定时任务
     */
    public void jobAdd(QuartzJobDto quartzJob) {
        try {
            // 构建job信息
            String className = quartzJob.getJobClassName();
            if (StrUtil.isBlank(className)) {
                throw new RuntimeException("定时任务类名不能为空");
            }

            Class<? extends Job> targetClazz = getClass(className);
            if (targetClazz == null) {
                throw new RuntimeException("定时任务类未找到");
            }
            // 启动调度器
            scheduler.start();

            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob()
                    .ofType(targetClazz)
                    .withIdentity(JOB_NAME + quartzJob.getId(), quartzJob.getSysCode())
                    .usingJobData("parameter", quartzJob.getParameter())
                    .withDescription(quartzJob.getDescription())
                    .build();


            // 表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
                    .cronSchedule(quartzJob.getCronExpression());

            //通过触发器名和cron 表达式创建 Trigger
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(JOB_NAME + quartzJob.getId(), quartzJob.getSysCode())
                    .withSchedule(cronScheduleBuilder)
                    .withDescription(quartzJob.getDescription())
                    .build();
            //执行定时任务
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.error("定时任务添加失败", e);
            throw new JobException("定时任务添加失败", e);
        }
    }


    /**
     * 一次性执行定时任务
     *
     * @param quartzJob 定时任务
     */
    public void jobRunOnce(QuartzJobDto quartzJob) {
        try {
            // 可能是一次性任务，所以不需要启动调度器
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + quartzJob.getId(), quartzJob.getSysCode());
            // 通过触发器名和组名获取Trigger
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 是否存在
            boolean exists = trigger != null;
            if (trigger == null) {
                jobAdd(quartzJob);
            }
            // 重新执行
            scheduler.triggerJob(JobKey.jobKey(JOB_NAME + quartzJob.getId(), quartzJob.getSysCode()));
            if (!exists) {
                jobDelete(quartzJob.getId(), quartzJob.getSysCode());
            }
        } catch (Exception e) {
            log.error("定时任务执行失败", e);
            throw new JobException("定时任务执行失败", e);
        }
    }

    /**
     * 暂停定时任务
     *
     * @param id .
     */
    public void jobPause(String id, String sysCode) {
        JobKey jobKey = JobKey.jobKey(JOB_NAME + id, sysCode);
        try {
            scheduler.pauseJob(jobKey);
        } catch (Exception e) {
            log.error("定时任务暂停失败", e);
            throw new JobException("定时任务暂停失败", e);
        }
    }

    /**
     * 恢复定时任务
     *
     * @param job .
     */
    public void jobResume(QuartzJobDto job) {
        JobKey jobKey = JobKey.jobKey(JOB_NAME + job.getId(), job.getSysCode());
        try {
            // 任务是否存在，存在则恢复，不存在则添加
            if (!scheduler.checkExists(jobKey)) {
                jobAdd(job);
            }
            scheduler.resumeJob(jobKey);
        } catch (Exception e) {
            log.error("定时任务恢复失败", e);
            throw new JobException("定时任务恢复失败", e);
        }
    }

    /**
     * 删除定时任务
     *
     * @param id .
     */
    public void jobDelete(String id, String sysCode) {
        JobKey jobKey = JobKey.jobKey(JOB_NAME + id, sysCode);
        try {
            // 停止触发器
            scheduler.pauseTrigger(TriggerKey.triggerKey(JOB_NAME + id, sysCode));
            // 移除触发器
            scheduler.unscheduleJob(TriggerKey.triggerKey(JOB_NAME + id, sysCode));
            // 删除任务
            scheduler.deleteJob(jobKey);
        } catch (Exception e) {
            log.error("定时任务删除失败", e);
            throw new JobException("定时任务删除失败", e);
        }
    }

    /**
     * 实例化指定任务实现类
     *
     * @param jobClassName 任务实现类全名/bean名称
     * @return 任务实现类实例
     * @throws Exception .
     */
    @SuppressWarnings("unchecked")
    private Class<? extends Job> getClass(String jobClassName) throws Exception {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(jobClassName);
        } catch (Exception e) {
            clazz = beanFactory.getType(jobClassName);
        }
        return (Class<? extends Job>) clazz;
    }
}