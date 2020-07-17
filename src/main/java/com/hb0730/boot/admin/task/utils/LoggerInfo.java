package com.hb0730.boot.admin.task.utils;

import com.hb0730.boot.admin.commons.constant.enums.SystemStatusEnum;
import com.hb0730.boot.admin.utils.ExceptionUtil;
import com.hb0730.boot.admin.manager.AsyncManager;
import com.hb0730.boot.admin.manager.factory.AsyncFactory;
import com.hb0730.boot.admin.project.monitor.job.model.entity.SystemJobLogEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 日志记录
 *
 * @author bing_huang
 * @date 2020/05/27 17:23
 * @since V2.0
 */
public class LoggerInfo {
    /**
     * 线程本地变量
     */
    private static final ThreadLocal<Date> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 执行前
     */
    public static void before() {
        THREAD_LOCAL.set(new Date());
    }

    /**
     * <p>
     * 执行后
     * </p>
     *
     * @param taskId 任务id
     * @param e      异常信息
     */
    public static void after(String taskId, Exception e) {
        Date startTime = THREAD_LOCAL.get();
        THREAD_LOCAL.remove();
        SystemJobLogEntity entity = new SystemJobLogEntity();
        entity.setJobId(Long.valueOf(taskId));
        entity.setStartTime(startTime);
        entity.setStopTime(new Date());
        if (e != null) {
            entity.setStatus(SystemStatusEnum.FAIL.getValue());
            String errorMsg = StringUtils.substring(ExceptionUtil.getExceptionMessage(e), 0, 2000);
            entity.setExceptionInfo(errorMsg);
        } else {
            entity.setStatus(SystemStatusEnum.SUCCESS.getValue());
        }
        long runMs = entity.getStopTime().getTime() - entity.getStartTime().getTime();
        entity.setJobMessage("总共耗时：" + runMs + "毫秒");
        AsyncManager.me().execute(AsyncFactory.recordJobLog(entity));
    }
}
