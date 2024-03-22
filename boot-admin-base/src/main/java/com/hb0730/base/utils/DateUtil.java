package com.hb0730.base.utils;

import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/1/23
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

}
