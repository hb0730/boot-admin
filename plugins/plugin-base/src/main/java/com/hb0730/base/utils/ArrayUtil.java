package com.hb0730.base.utils;

/**
 * 数组工具类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/12/12
 */
public class ArrayUtil extends cn.hutool.core.util.ArrayUtil {

    /**
     * 判断数组中是否包含指定的字符串
     *
     * @param array      数组
     * @param substrings 字符串
     * @return 是否包含
     */
    public static Boolean containsAnySubstring(String[] array, String... substrings) {
        for (String substring : substrings) {
            for (String s : array) {
                if (s.contains(substring)) {
                    return true;
                }
            }
        }
        return false;
    }
}
