package com.hb0730.boot.admin.manager.factory;

import com.hb0730.boot.admin.project.monitor.operation.model.entity.OperLogEntity;
import com.hb0730.boot.admin.project.monitor.operation.service.IOperLogService;
import com.hb0730.boot.admin.project.system.quartz.model.entity.JobLogEntity;
import com.hb0730.boot.admin.project.system.quartz.service.IJobLogService;
import com.hb0730.commons.spring.SpringContextUtils;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class AsyncFactory {

    /**
     * 记录定时任务日志
     *
     * @param entity 定时任务信息
     * @return {@link TimerTask}
     */
    public static TimerTask recordJobLog(JobLogEntity entity) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringContextUtils.getBean(IJobLogService.class).save(entity);
            }
        };
    }

    /**
     * 记录操作日志
     *
     * @param entity 操作日志
     * @return {@link TimerTask}
     */
    public static TimerTask recordOperLog(OperLogEntity entity) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringContextUtils.getBean(IOperLogService.class).save(entity);
            }
        };
    }
}
