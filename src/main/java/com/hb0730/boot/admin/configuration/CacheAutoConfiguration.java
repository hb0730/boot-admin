package com.hb0730.boot.admin.configuration;

import com.hb0730.boot.admin.cache.Cache;
import com.hb0730.boot.admin.cache.exception.BootCacheException;
import com.hb0730.boot.admin.cache.impl.local.InMemoryCacheStore;
import com.hb0730.boot.admin.cache.impl.remote.RedisSpringDataCache;
import com.hb0730.boot.admin.cache.support.redis.springdata.RedisSpringDataCacheConfig;
import com.hb0730.boot.admin.configuration.properties.BootAdminProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.util.Map;

/**
 * @author bing_huang
 * @date 2020/07/28 7:44
 * @since V1.0
 */
@Configuration
@AllArgsConstructor
public class CacheAutoConfiguration {
    private final BootAdminProperties properties;
    private final ApplicationContext applicationContext;

    @Bean
    @ConditionalOnMissingBean(name = "bootCache")
    public <K, V> Cache<K, V> bootCache() {
        Cache<K, V> cache;
        switch (properties.getCacheConfig().getCache()) {
            case "jedis":
                cache = null;
                break;
            case "spring-redis":
                cache = initSpringRedisData();
                break;
            case "memory":
            default:
                cache = new InMemoryCacheStore<K, V>();
                break;
        }
        return cache;
    }

    private <K, V> Cache<K, V> initSpringRedisData() {
        Map<String, RedisConnectionFactory> beans = applicationContext.getBeansOfType(RedisConnectionFactory.class);
        if (beans.isEmpty()) {
            throw new BootCacheException("no RedisConnectionFactory in spring context");
        }
        RedisConnectionFactory factory = beans.values().iterator().next();
        RedisSpringDataCacheConfig<K, V> cacheConfig = new RedisSpringDataCacheConfig<>();
        cacheConfig.setConnectionFactory(factory);
        return new RedisSpringDataCache<K, V>(cacheConfig);
    }
}
