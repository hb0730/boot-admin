package com.hb0730.base.utils;

import cn.hutool.core.text.CharSequenceUtil;

import java.util.concurrent.Callable;

/**
 * 字符串工具
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
public class StrUtil extends cn.hutool.core.util.StrUtil {
    /**
     * 大写数字下划线组成转为小写
     */
    public static boolean isCapitalMode(String str) {
        return CharSequenceUtil.isNotBlank(str) && str.matches("^[A-Z0-9_]+$");
    }

    /**
     * 是否混合模式
     *
     * @param str 字符串
     * @return 是否混合模式
     */
    public static boolean isMixedMode(String str) {
        return CharSequenceUtil.isNotBlank(str) && str.matches(".*[a-zA-Z]+.*");
    }

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

    /**
     * 获取字符串
     *
     * @param prefix       前缀
     * @param call         回调
     * @param defaultValue 默认值
     * @return 字符串
     */
    public static String getValue(String prefix, Callable<Object> call, String defaultValue) {
        try {
            Object result = call.call();
            String value = (result != null) ? result.toString() : null;
            if (hasEmpty(value)) {
                value = defaultValue;
            }
            return prefix + value;
        } catch (Exception e) {
            return defaultValue;
        }
    }


    /**
     * 任意一个是否为空
     *
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isAnyBlank(CharSequence... str) {
        for (CharSequence s : str) {
            if (isBlank(s)) {
                return true;
            }
        }
        return false;
    }
}
