package com.hb0730.boot.admin.task.spring;

import com.hb0730.boot.admin.task.utils.JobInvokeUtil;
import com.hb0730.boot.admin.task.utils.LoggerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @Override
    public void run() {
        LoggerInfo.before();
        try {
            JobInvokeUtil.invokeMethod(getTaskConstant());
            LoggerInfo.after(getTaskConstant().getTaskId(), null);
        } catch (Exception e) {
            logger.error("任务执行异常  - ：", e);
            LoggerInfo.after(getTaskConstant().getTaskId(), e);
        }
    }

}
