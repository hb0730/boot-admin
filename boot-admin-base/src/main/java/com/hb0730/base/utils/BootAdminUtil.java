package com.hb0730.base.utils;

import cn.hutool.core.util.StrUtil;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/1/23
 */
public class BootAdminUtil {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_yyyyMMdd_FORMAT = "yyyyMMdd";
    public static final String TIME_PERIOD_FORMAT = "HH:mm~HH:mm";
    public static final String TIME_FORMAT = "HH:mm";
    public static final String DATE_yyyyMMddHHmmss_FORMAT = "yyyyMMddHHmmss";
    public static final String REGEX_TIME_PERIOD = "^\\d{2}:\\d{2}~\\d{2}:\\d{2}$";
    public static final String REGEX_TIME_PERIOD2 = "^(0[0-9]|1[0-9]|2[0-4]):[0-5][0-9]~(0[0-9]|1[0-9]|2[0-4])" +
            ":[0-5][0-9]$";

    /**
     * 时间段验证
     *
     * @param value 时间段
     * @return 结果
     */
    public static boolean timePeriod(String value) {
        if (StrUtil.isBlank(value)) {
            return false;
        }
        return Pattern.matches(REGEX_TIME_PERIOD, value);
    }

    /**
     * 字符串时间转日期
     *
     * @param dateStr 字符串日期
     * @param format  格式
     * @return 时间
     */
    public static Date string2Date(String dateStr, String format) {
        try {
            return cn.hutool.core.date.DateUtil.parse(dateStr, format);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 字符串转日期,默认格式：yyyy-MM-dd
     *
     * @param dateStr 字符串日期
     * @return 时间
     */
    public static Date string2Date(String dateStr) {
        return string2Date(dateStr, DATE_FORMAT);
    }

    /**
     * 字符串转整数
     *
     * @param value .
     * @return .
     */
    public static Integer string2Integer(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 正则表达式：验证手机号
     */
    private static final String REGEX_MOBILE = "^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9]|400)[0-9]{8}$";

    /**
     * 校验手机号
     *
     * @param mobile 手机号
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isValidMobile(String mobile) {
        try {
            return Pattern.matches(REGEX_MOBILE, mobile);
        } catch (Throwable e) {
            return false;
        }
    }

}
