package com.hb0730.boot.admin.task.quartz.utils;

import com.hb0730.boot.admin.task.quartz.exceptions.QuartzException;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Date;

/**
 * cron表达式工具类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class CronUtils {
    /**
     * 是否符合quartz cron表达式
     *
     * @param cronExpression 被检测的cron
     * @return 是否有效符合
     */
    public static boolean isValid(final String cronExpression) {
        return CronExpression.isValidExpression(cronExpression);
    }

    /**
     * 返回下一个执行时间根据给定的Cron表达式
     *
     * @param cronExpression Cron表达式
     * @return Date 下次Cron表达式执行时间
     * @throws QuartzException 获取异常
     */
    public static Date getNextCExecution(final String cronExpression) {
        try {
            CronExpression cron = new CronExpression(cronExpression);
            return cron.getNextValidTimeAfter(new Date(System.currentTimeMillis()));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new QuartzException(e);
        }
    }

}
