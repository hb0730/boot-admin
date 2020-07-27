package com.hb0730.boot.admin.cache.config;

import com.hb0730.boot.admin.cache.support.serial.Serializer;
import com.hb0730.boot.admin.cache.support.serial.impl.JdkCacheSerializer;
import lombok.Data;

import java.util.function.Function;

/**
 * cache configuration
 *
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
    /**
     * 序列化
     */
    private Serializer serializer = JdkCacheSerializer.INSTANCE;
}
