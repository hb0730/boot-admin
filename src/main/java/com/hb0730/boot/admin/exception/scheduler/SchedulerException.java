package com.hb0730.boot.admin.exception.scheduler;

import com.hb0730.boot.admin.exception.BaseException;

/**
 * 定时任务异常
 *
 * @author bing_huang
 * @date 2020/06/28 15:28
 * @since V1.0
 */
public class SchedulerException extends BaseException {
    public SchedulerException(String message) {
        super(message);
    }

    public SchedulerException(String message, Throwable e) {
        super(message, e);
    }
}
