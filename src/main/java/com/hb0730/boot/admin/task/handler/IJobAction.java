package com.hb0730.boot.admin.task.handler;

import com.hb0730.boot.admin.commons.enums.JobActionEnum;
import org.quartz.SchedulerException;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author bing_huang
 */
public interface IJobAction {

    /**
     * 执行
     *
     * @param ids ids集合
     * @throws SchedulerException 执行异常
     */
    void run(@NonNull Collection<? extends Serializable> ids) throws SchedulerException;

    /**
     * 是否支持
     *
     * @param actionEnum {@link JobActionEnum}
     * @return 是否支持
     */
    boolean support(JobActionEnum actionEnum);

    /**
     * 获取对应的action
     *
     * @return {@link JobActionEnum}
     */
    JobActionEnum getActionEnum();
}
