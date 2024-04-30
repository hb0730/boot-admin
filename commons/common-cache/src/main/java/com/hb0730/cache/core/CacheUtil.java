package com.hb0730.cache.core;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
public interface CacheUtil {
    /**
     * 缓存key的失效时间
     * <p> 不设置失效时间 == 永久有效 ；
     * XXX: setObject方法设为-1时，经封装后代表永久有效
     * <p> 单位：秒
     */
    int EXPIRE_TIME_DEFAULT = -1;
    /**
     * 缓存key的失效时间
     * 30天=60*60*24*30
     */
    int EXPIRE_TIME = 60 * 60 * 24 * 30;
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
        return kv.getPrefix() + concatKeys(keys);
    }

    static String concatKeys(Object... keys) {
        return Arrays.stream(keys).map(String::valueOf).collect(Collectors.joining(CACHE_SPLICE_COLON));
    }

    interface KeyValue {
        /**
         * KEY前缀
         *
         * @return .
         */
        String getPrefix();

        /**
         * 过期时间（秒）
         *
         * @return .
         */
        int getExpire();

        /**
         * 描述
         *
         * @return .
         */
        String getDesc();
    }
}