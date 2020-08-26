package com.hb0730.boot.admin.configuration;

import com.hb0730.commons.cache.Cache;
import com.hb0730.commons.cache.impl.remote.RedisSpringDataCache;
import com.hb0730.commons.cache.support.redis.springdata.RedisSpringDataCacheConfig;
import com.hb0730.commons.cache.support.serial.impl.Jackson2JsonCacheWrapperSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * Cache 配置
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Configuration
public class CacheConfiguration {

    /**
     * redis cache
     *
     * @param factory redis链接
     * @param <K>     Key类型
     * @param <V>     value 类型
     * @return {@link Cache}
     */
    @Bean
    @ConditionalOnMissingBean(name = "redisCache")
    public <K, V> Cache<K, V> redisCache(RedisConnectionFactory factory) {
        RedisSpringDataCacheConfig<K, V> config = new RedisSpringDataCacheConfig<>();
        config.setConnectionFactory(factory);
        config.setSerializer(Jackson2JsonCacheWrapperSerializer.JSON_STRING_SERIALIZER);
        return new RedisSpringDataCache<>(config);
    }
}
