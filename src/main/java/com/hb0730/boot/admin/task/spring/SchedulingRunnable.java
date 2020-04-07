package com.hb0730.boot.admin.task.spring;

import com.hb0730.boot.admin.commons.constant.SystemStatusEnum;
import com.hb0730.boot.admin.commons.utils.ExceptionUtil;
import com.hb0730.boot.admin.manager.AsyncManager;
import com.hb0730.boot.admin.manager.factory.AsyncFactory;
import com.hb0730.boot.admin.project.monitor.job.model.entity.SystemJobLogEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * <p>
 * 定时任务运行类
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class SchedulingRunnable implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(SchedulingRunnable.class);
    private TaskConstant taskConstant;

    public SchedulingRunnable(TaskConstant taskConstant) {
        this.taskConstant = taskConstant;
    }

    public SchedulingRunnable() {
    }

    public TaskConstant getTaskConstant() {
        return taskConstant;
    }

    public void setTaskConstant(TaskConstant taskConstant) {
        this.taskConstant = taskConstant;
    }

    /**
     * 线程本地变量
     */
    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    /**
     * 执行前
     */
    protected void before() {
        threadLocal.set(new Date());
    }

    @Override
    public void run() {
        before();
        try {
            JobInvokeUtil.invokeMethod(getTaskConstant());
            after(getTaskConstant(), null);
        } catch (Exception e) {
            logger.error("任务执行异常  - ：", e);
            after(getTaskConstant(), e);
        }
    }

    /**
     * <p>
     * 执行后
     * </p>
     *
     * @param taskConstant 任务信息
     * @param e            异常信息
     */
    protected void after(TaskConstant taskConstant, Exception e) {
        Date startTime = threadLocal.get();
        threadLocal.remove();
        SystemJobLogEntity entity = new SystemJobLogEntity();
        entity.setJobId(Long.valueOf(taskConstant.getTaskId()));
        entity.setStartTime(startTime);
        entity.setStopTime(new Date());
        if (e != null) {
            entity.setStatus(SystemStatusEnum.FAIL.ordinal());
            String errorMsg = StringUtils.substring(ExceptionUtil.getExceptionMessage(e), 0, 2000);
            entity.setExceptionInfo(errorMsg);
        } else {
            entity.setStatus(SystemStatusEnum.SUCCESS.ordinal());
        }
        long runMs = entity.getStopTime().getTime() - entity.getStartTime().getTime();
        entity.setJobMessage("总共耗时：" + runMs + "毫秒");
        AsyncManager.me().execute(AsyncFactory.recordJobLog(entity));
    }
}
