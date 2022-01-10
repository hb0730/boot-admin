package com.hb0730.boot.admin.task.quartz.job;

import cn.hutool.core.bean.BeanUtil;
import com.hb0730.boot.admin.manager.AsyncManager;
import com.hb0730.boot.admin.manager.factory.AsyncFactory;
import com.hb0730.boot.admin.project.system.quartz.model.entity.JobEntity;
import com.hb0730.boot.admin.project.system.quartz.model.entity.JobLogEntity;
import com.hb0730.boot.admin.task.quartz.constant.ScheduleConstants;
import com.hb0730.commons.lang.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 抽象quartz调用
 *
 * @author bing_huang
 * @since 3.0.0
 */
public abstract class AbstractQuartzJob extends QuartzJobBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractQuartzJob.class);
    /**
     * 线程本地变量
     */
    private static final ThreadLocal<LocalDateTime> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    protected void executeInternal(@Nonnull JobExecutionContext context) {
        LOGGER.debug("job start >>>>>>>>>>>");
        Object obj = context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES);
        if (null != obj) {
            JobEntity job = BeanUtil.toBean(obj, JobEntity.class);
            if (null != job) {
                try {
                    before(context, job);
                    doExecute(context, job);
                    after(context, job, null);
                } catch (Exception e) {
                    LOGGER.error("任务执行异常", e);
                    after(context, job, e);
                }
            }
        }
    }

    /**
     * 执行方法，由子类重载
     *
     * @param context 工作执行上下文对象
     * @param job     系统计划任务
     * @throws Exception 执行异常
     */
    abstract void doExecute(JobExecutionContext context, JobEntity job) throws Exception;

    /**
     * 执行前
     *
     * @param context 工作执行上下文对象
     * @param job     系统计划任务
     */
    protected void before(JobExecutionContext context, JobEntity job) {
        THREAD_LOCAL.set(LocalDateTime.now());
    }

    /**
     * 执行后
     *
     * @param context 工作执行上下文对象
     * @param job     系统计划任务
     * @param e       执行异常
     */
    protected void after(JobExecutionContext context, JobEntity job, Exception e) {
        LocalDateTime startTime = THREAD_LOCAL.get();
        THREAD_LOCAL.remove();
        JobLogEntity entity = new JobLogEntity();
        entity.setJobId(job.getId());
        entity.setJobName(job.getName());
        entity.setJobGroup(job.getGroup());
        entity.setInvokeTarget(job.getBeanName() + "." + job.getBeanMethod());
        entity.setMethodParams(job.getMethodParams());
        entity.setStartTime(startTime);
        entity.setEndTime(LocalDateTime.now());
        long runMs = entity.getEndTime()
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli() - entity.getStartTime()
                .atZone(ZoneId.systemDefault())
                .toInstant().toEpochMilli();
        entity.setJobMessage(entity.getJobName() + " 总共耗时：" + runMs + "毫秒");
        if (null != e) {
            entity.setStatus(0);
            String message = ExceptionUtils.getExceptionMessage(e);
            String errMessage = StringUtils.substring(message, 0, 2000);
            entity.setExceptionInfo(errMessage);
        } else {
            entity.setStatus(1);
        }
        // 保存
        AsyncManager.me().execute(AsyncFactory.recordJobLog(entity));
    }
}
