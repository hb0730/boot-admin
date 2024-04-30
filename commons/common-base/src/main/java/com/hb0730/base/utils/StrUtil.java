package com.hb0730.base.utils;

import cn.hutool.core.text.CharSequenceUtil;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
public class StrUtil extends cn.hutool.core.util.StrUtil {
    /**
     * 获取字符串
     *
     * @param str          字符串
     * @param defaultValue 默认值,如果字符串为空则返回默认值
     * @return 字符串
     */
    public static String getStr(String str, String defaultValue) {
        return CharSequenceUtil.isBlank(str) ? defaultValue : str;
    }
}