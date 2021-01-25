package com.hb0730.boot.admin.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb0730.commons.cache.Cache;
import com.hb0730.commons.cache.impl.remote.RedisSpringDataCache;
import com.hb0730.commons.cache.support.redis.springdata.RedisSpringDataCacheConfig;
import com.hb0730.commons.cache.support.serializer.impl.GenericJackson2JsonCacheWrapperSerializer;
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
     * @param mapper  {@link ObjectMapper} jacksonMapper
     * @return {@link Cache}
     */
    @Bean
    @ConditionalOnMissingBean(name = "redisCache")
    public <K, V> Cache<K, V> redisCache(RedisConnectionFactory factory, ObjectMapper mapper) {
        ObjectMapper copyMapper = mapper.copy();
        RedisSpringDataCacheConfig<K, V> config = new RedisSpringDataCacheConfig<>();
        config.setConnectionFactory(factory);
//        Jackson2JsonCacheWrapperSerializer serializer = new Jackson2JsonCacheWrapperSerializer(true, mapper);
        GenericJackson2JsonCacheWrapperSerializer serializer = new GenericJackson2JsonCacheWrapperSerializer(true, copyMapper);
        config.setSerializer(serializer);
        return new RedisSpringDataCache<>(config);
    }
}
