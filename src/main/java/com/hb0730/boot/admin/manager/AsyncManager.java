package com.hb0730.boot.admin.manager;

import com.hb0730.boot.admin.commons.utils.Threads;
import com.hb0730.boot.admin.commons.utils.spring.SpringUtils;
import com.hb0730.boot.admin.task.spring.SchedulingRunnable;
import com.hb0730.boot.admin.task.spring.TaskConstant;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 异步线程
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */

public class AsyncManager {
    /**
     * 操作延迟10毫秒
     */
    private final int OPERATE_DELAY_TIME = 10;

    /**
     * 异步操作任务调度线程池
     */
    private ScheduledExecutorService executor = SpringUtils.getBean("scheduledExecutorService");

    /**
     * 单例模式
     */
    private AsyncManager() {
    }

    private static AsyncManager me = new AsyncManager();

    public static AsyncManager me() {
        return me;
    }

    /**
     * 执行任务
     *
     * @param task 任务
     */
    public void execute(TimerTask task) {
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }
    /**
     * 立即执行job
     *
     * @param constant 任务参数
     */
    public void executorJob(TaskConstant constant) {
        SchedulingRunnable runnable = new SchedulingRunnable(constant);
        executor.schedule(runnable, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        Threads.shutdownAndAwaitTermination(executor);
    }
}
