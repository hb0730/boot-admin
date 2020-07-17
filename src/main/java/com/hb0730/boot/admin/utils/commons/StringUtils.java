package com.hb0730.boot.admin.utils.commons;

import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class StringUtils {
    /**
     * 去空格
     */
    public static String trim(String str)
    {
        return (str == null ? "" : str.trim());
    }
    /**
     * 是否包含字符串
     *
     * @param str 验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs)
    {
        if (str != null && strs != null)
        {
            for (String s : strs)
            {
                if (str.equalsIgnoreCase(trim(s)))
                {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 确保字符串包含后缀
     *
     * @param string 字符串不能为空
     * @param suffix 字符串不能为空
     * @return 字符串包含指定的后缀
     */
    @NonNull
    public static String ensureSuffix(@NonNull String string, @NonNull String suffix) {
        Assert.hasText(string, "String must not be blank");
        Assert.hasText(suffix, "Suffix must not be blank");

        return org.apache.commons.lang3.StringUtils.removeEnd(string, suffix) + suffix;
    }
}
