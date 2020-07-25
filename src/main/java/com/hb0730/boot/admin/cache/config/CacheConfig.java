package com.hb0730.boot.admin.cache.config;

import lombok.Data;

import java.util.function.Function;

/**
 * @author bing_huang
 * @date 2020/07/24 8:39
 * @since V1.0
 */
@Data
public class CacheConfig<K, V> {
    /**
     * key 转换器
     */
    private Function<K, V> keyConverter;
}
