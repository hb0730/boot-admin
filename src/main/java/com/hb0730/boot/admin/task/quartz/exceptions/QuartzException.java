package com.hb0730.boot.admin.task.quartz.exceptions;

/**
 * quartz 异常
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class QuartzException extends RuntimeException {
    public QuartzException(Throwable cause) {
        super(cause);
    }
}
