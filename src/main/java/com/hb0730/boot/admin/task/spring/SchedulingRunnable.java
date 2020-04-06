package com.hb0730.boot.admin.task.spring;

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
            after(null);
        } catch (Exception e) {
            logger.error("任务执行异常  - ：", e);
            after(e);
        }
    }

    /**
     * 执行后
     */
    protected void after(Exception e) {
        Date startTime = threadLocal.get();
        threadLocal.remove();

    }
}
