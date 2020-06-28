package com.hb0730.boot.admin.task.quartz;

import com.google.common.collect.Lists;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.validation.constraints.NotNull;
import java.util.*;

import static com.hb0730.boot.admin.task.quartz.QuartzConstant.JobInfo.*;


/**
 * quartz 定时任务
 *
 * @author bing_huang
 * @date 2020/05/27 8:18
 * @since V2.0
 */
public class QuartzUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzUtils.class);

    /**
     * 增加一个job
     *
     * @param jobClass  任务实现类
     * @param scheduler 定时任务
     * @param model     调度参数
     */
    public static void addJob(@NotNull Class<? extends QuartzJobBean> jobClass, @NotNull Scheduler scheduler, @NotNull JobModel model) throws SchedulerException {
        if (!CronUtils.isValid(model.getCron())) {
            throw new com.hb0730.boot.admin.exception.scheduler.SchedulerException("cron 表达式不正确");
        }
        createScheduleJob(jobClass, scheduler, model);
    }

    /**
     * 修改 一个job
     *
     * @param jobClass  任务实现类
     * @param scheduler 定时任务
     * @param model     调度参数
     */
    public static void updateJob(@NotNull Class<? extends QuartzJobBean> jobClass, @NotNull Scheduler scheduler, @NotNull JobModel model) throws SchedulerException {
        // 删除任务
        deleteJob(scheduler, model.getJobName(), model.getJobGroupName());
        // 在添加
        addJob(jobClass, scheduler, model);
    }

    /**
     * 删除一个job
     *
     * @param jobName      任务名称
     * @param jobGroupName 任务组名
     */
    public static void deleteJob(@NotNull Scheduler scheduler, @NotNull String jobName, @NotNull String jobGroupName) throws SchedulerException {
        JobKey jobKey = getJobKey(jobName, jobGroupName);
        if (scheduler.checkExists(jobKey)) {
            LOGGER.debug("删除时任务 {},{}", jobName, jobGroupName);

            scheduler.deleteJob(jobKey);
        }
    }


    /**
     * 暂停一个job
     *
     * @param jobName      任务名称
     * @param jobGroupName 任务组名
     */
    public static void pauseJob(@NotNull Scheduler scheduler, @NotNull String jobName, @NotNull String jobGroupName) throws SchedulerException {
        JobKey jobKey = getJobKey(jobName, jobGroupName);
        if (scheduler.checkExists(jobKey)) {
            LOGGER.debug("暂停时任务 {},{}", jobName, jobGroupName);

            scheduler.pauseJob(jobKey);
        }
    }

    /**
     * 恢复一个job
     *
     * @param jobName      任务名称
     * @param jobGroupName 任务组名
     */
    public static void resumeJob(@NotNull Scheduler scheduler, @NotNull String jobName, @NotNull String jobGroupName) throws SchedulerException {
        JobKey jobKey = getJobKey(jobName, jobGroupName);
        if (scheduler.checkExists(jobKey)) {
            LOGGER.debug("恢复定时任务 {},{}", jobName, jobGroupName);

            scheduler.resumeJob(jobKey);
        }
    }

    /**
     * 立即执行一个job
     *
     * @param jobName      任务名称
     * @param jobGroupName 任务组名
     */
    public static void runJobNow(@NotNull Scheduler scheduler, @NotNull String jobName, @NotNull String jobGroupName) throws SchedulerException {
        JobKey jobKey = getJobKey(jobName, jobGroupName);
        if (scheduler.checkExists(jobKey)) {
            LOGGER.debug("立即执行定时任务 {},{}", jobName, jobGroupName);
            scheduler.triggerJob(jobKey);
        }
    }


    /**
     * 获取所有计划中的任务列表
     *
     * @return 任务列表
     */
    public static List<Map<String, Object>> queryAllJob(@NotNull Scheduler scheduler) throws SchedulerException {
        List<Map<String, Object>> jobList = null;
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        jobList = new ArrayList<Map<String, Object>>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {

                Map<String, Object> map = new HashMap<>();
                map.put(JOB_NAME, jobKey.getName());
                map.put(JOB_GROUP_NAME, jobKey.getGroup());
                map.put(DESCRIPTION, "触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                map.put(JOB_STATUS, triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    map.put(JOB_TIME, cronExpression);
                }
                jobList.add(map);
            }
        }
        return jobList;
    }

    /**
     * 获取所有计划中的任务列表的job key
     *
     * @param scheduler 定时任务
     * @return job key 列表
     */
    public static <T> List<T> queryAllJobKeyName(@NotNull Scheduler scheduler) throws SchedulerException {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<T> keys = new ArrayList<>();
        for (JobKey jobKey : jobKeys) {
            String name = jobKey.getName();
            keys.add((T) name);
        }
        return keys;
    }


    /**
     * 获取所有正在运行的job
     *
     * @return 任务列表
     */
    public static List<Map<String, Object>> queryRunJob(@NotNull Scheduler scheduler) throws SchedulerException {
        List<Map<String, Object>> jobList = null;
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        jobList = new ArrayList<Map<String, Object>>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            Map<String, Object> map = new HashMap<String, Object>();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            map.put(JOB_NAME, jobKey.getName());
            map.put(JOB_GROUP_NAME, jobKey.getGroup());
            map.put(DESCRIPTION, "触发器:" + trigger.getKey());
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            map.put(JOB_STATUS, triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                map.put(JOB_TIME, cronExpression);
            }
            jobList.add(map);
        }
        return jobList;
    }

    /**
     * 获取所有正在运行的job key name
     *
     * @param scheduler 任务调度
     * @return job key name集合
     */
    public static <T> List<T> queryRunJobKeyName(@NotNull Scheduler scheduler) throws SchedulerException {
        List<T> keys = Lists.newArrayList();
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        for (JobExecutionContext executingJob : executingJobs) {
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            keys.add((T) jobKey.getName());
        }
        return keys;
    }

    /**
     * 创建定时任务
     */
    private static void createScheduleJob(Class<? extends QuartzJobBean> clazz, Scheduler scheduler, JobModel model) throws SchedulerException {
        // 构建job信息
        String jobName = model.getJobName();
        String groupName = model.getJobGroupName();
        JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(getJobKey(jobName, groupName)).build();

        LOGGER.debug("创建定时任务， {}#{}", jobName, groupName);
        // 表达式调度构建器
        CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(model.getCron());
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(jobName, groupName)).withSchedule(cronSchedule).build();

        // 放入参数，运行时的方法可以获取
        jobDetail.getJobDataMap().put(QuartzConstant.ParamsName.CLASS_NAME, model.getBeanName());
        jobDetail.getJobDataMap().put(QuartzConstant.ParamsName.METHOD_NAME, model.getMethodName());
        jobDetail.getJobDataMap().put(QuartzConstant.ParamsName.PARAMS_NAME, model.getParams());
        jobDetail.getJobDataMap().put(QuartzConstant.ParamsName.TASK_ID, model.getJobId());

        // 判断是否存在
        if (scheduler.checkExists(getJobKey(jobName, groupName))) {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(getJobKey(jobName, groupName));
        }
        scheduler.scheduleJob(jobDetail, trigger);

        // 暂停任务
    }

    /**
     * 构建任务键对象
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组
     * @return job key
     */
    private static JobKey getJobKey(String jobName, String jobGroup) {
        return JobKey.jobKey(jobName, jobGroup);
    }

    /**
     * 构建任务触发对象
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组
     * @return 触发对象
     */
    public static TriggerKey getTriggerKey(String jobName, String jobGroup) {
        return TriggerKey.triggerKey(jobName, jobGroup);
    }

}
