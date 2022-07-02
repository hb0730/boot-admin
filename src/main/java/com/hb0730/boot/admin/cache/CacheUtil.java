package com.hb0730.boot.admin.cache;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 缓存定义场所
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/2
 * @since 1.0.0
 */
public interface CacheUtil {
    /**
     * 缓存key的失效时间
     * <p> 不设置失效时间 == 永久有效 ；
     * XXX: setObject方法设为-1时，经封装后代表永久有效
     * <p> 单位：秒
     */
    long EXPIRE_TIME_DEFAULT = -1;

    /**
     * 工单号在缓存存储时间 90天
     */
    long ORDER_TIME_OUT_LIMIT = 60 * 60 * 24 * 30;
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
