package com.hb0730.boot.admin.task.quartz;

import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Date;

/**
 * 表达式工具类
 *
 * @author bing_huang
 * @date 2020/05/27 10:12
 * @since V2.0
 */
public class CronUtils {

    /**
     * cron是否合法
     *
     * @param cron cron表达式
     * @return 表达式是否合法
     */
    public static boolean isValid(String cron) {
        return CronExpression.isValidExpression(cron);
    }


    /**
     * 获取下一个执行的时间
     *
     * @param cron cron表达式
     * @return 下一次执行的时间
     */
    public static Date getNextExecution(String cron) {
        CronExpression next = null;
        try {
            next = new CronExpression(cron);
            return next.getNextValidTimeAfter(new Date(System.currentTimeMillis()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
