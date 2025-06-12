package com.hb0730.base.pool;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/14
 */
public interface StrPool extends cn.hutool.core.text.StrPool {
    /**
     * 空字符串
     */
    String EMPTY = "";

    static String EQUALS = "=";

    /**
     * utf-8
     */
    Charset CHARSET_UTF_8 = StandardCharsets.UTF_8;
    /**
     * utf-8
     */
    String UTF_8 = "UTF-8";

    /**
     * {@code "*"}
     */
    String ASTERISK = "*";

    /**
     * {@code "/**"}
     */
    String PATH_PATTERN = "/**";

    /**
     * {@code "\n"}
     */
    String NEWLINE = "\n";
}
