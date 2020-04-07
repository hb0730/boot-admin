package com.hb0730.boot.admin.commons.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class ExceptionUtil {
    /**
     * 获取exception的详细错误信息。
     */
    public static String getExceptionMessage(Throwable e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String str = sw.toString();
        return str;
    }
}
