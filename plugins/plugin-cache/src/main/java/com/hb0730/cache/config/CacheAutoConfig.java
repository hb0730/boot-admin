package com.hb0730.cache.config;


import com.hb0730.cache.core.CacheUtil;
import com.hb0730.cache.core.ICache;
import com.hb0730.cache.redis.RedisCache;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/23
 */
@AutoConfiguration
@EnableConfigurationProperties(CacheProperties.class)
@ConditionalOnProperty(prefix = "zoom.cache", name = "enabled", havingValue = "true", matchIfMissing = true)
public class CacheAutoConfig {

    /**
     * redis缓存
     *
     * @return {@link RedisCache}
     */
    @ConditionalOnMissingBean
    @Bean
    public RedisCache redisCache(RedisTemplate<String, Object> redisTemplate) {
        return new RedisCache(redisTemplate);
    }

    /**
     * 缓存工具类
     *
     * @return {@link CacheUtil}
     */
    @ConditionalOnMissingBean
    @Bean
    public CacheUtil cache(ICache redisCache, CacheProperties cacheProperties) {
        return new CacheUtil(
                redisCache,
                cacheProperties.getPrefix()
        );
    }
}
