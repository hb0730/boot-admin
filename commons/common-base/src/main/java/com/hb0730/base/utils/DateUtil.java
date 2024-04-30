package com.hb0730.base.utils;

import cn.hutool.core.date.DateTime;

import java.util.Calendar;
import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
public class DateUtil extends cn.hutool.core.date.DateUtil {

    /**
     * 字符串转日期
     *
     * @param dateStr 字符串日期
     * @return 时间
     */
    public static Date string2Date(String dateStr) {
        try {
            return cn.hutool.core.date.DateUtil.parse(dateStr, "yyyy-MM-dd");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 默认方式表示的系统当前日期，具体格式：年-月-日
     *
     * @return .
     */
    public static String formatDate() {
        return cn.hutool.core.date.DateUtil.format(new Date(), "yyyy-MM-dd");
    }

    /**
     * 获取当前时间，时份秒为0
     * <p>
     * 例如：2024-03-22 12:12:12 -> 2024-03-22 00:00:00
     *
     * @return 时间
     */
    public static Date nowDate() {
        DateTime date = cn.hutool.core.date.DateUtil.date();
        Calendar calendar = date.toCalendar();
        calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                0,
                0,
                0
        );
        return calendar.getTime();
    }

}