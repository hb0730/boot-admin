package com.hb0730.boot.admin.config.cache;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/30
 */
public interface CacheUtil {
    /**
     * 缓存关键字连接符号:冒号
     */
    String CACHE_SPLICE_COLON = ":";


    /**
     * 缓存KEY组装器
     *
     * @param kv   缓存枚举
     * @param keys 缓存key拼接字符
     */
    default String getCacheKey(KeyValue kv, Object... keys) {
        return buildKeys(kv, keys);
    }

    static String buildKeys(KeyValue kv, Object... keys) {
        if (keys.length == 0) {
            return kv.getPrefix();
        }
        return kv.getPrefix() + CACHE_SPLICE_COLON + concatKeys(keys);
    }

    static String concatKeys(Object... keys) {
        return Arrays.stream(keys).map(String::valueOf).collect(Collectors.joining(CACHE_SPLICE_COLON));
    }
}
