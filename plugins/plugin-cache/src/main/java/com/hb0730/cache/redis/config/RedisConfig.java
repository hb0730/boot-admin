package com.hb0730.cache.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb0730.cache.config.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/23
 */
@Configuration
@EnableConfigurationProperties(CacheProperties.class)

@EnableCaching
public class RedisConfig implements CachingConfigurer {

    @ConditionalOnProperty(prefix = "zoom.cache", name = "enable", havingValue = "true", matchIfMissing = true)
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory,
                                     CacheProperties cacheProperties,
                                     CustomCacheConfig customCacheConfig) {

        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration
                .defaultCacheConfig()
                // 设置缓存有效期6小时
                .entryTtl(Duration.ofHours(6L))
                // 设置key序列化器
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // 设置value序列化器
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .computePrefixWith(cacheName -> cacheProperties.getPrefix() + ":" + cacheName + ":");

        RedisCacheWriter cacheWriter = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
        HashMap<String, RedisCacheConfiguration> cacheConfig = new HashMap<>();

        Map<String, Long> customCacheConfigs = customCacheConfig.getCustomCacheConfigs();
        for (String key : customCacheConfigs.keySet()) {
            // key 即为cacheName @see AbstractCacheManager#getCache(String name)
            cacheConfig.put(key, defaultCacheConfig
                    .entryTtl(Duration.ofSeconds(customCacheConfigs.get(key)))
                    .disableCachingNullValues());
        }

        return RedisCacheManager.builder(cacheWriter)
                .cacheDefaults(defaultCacheConfig)
                .withInitialCacheConfigurations(cacheConfig)
                .transactionAware()
                .build();
    }


    @ConditionalOnProperty(prefix = "zoom.cache", name = "enable", havingValue = "true", matchIfMissing = true)
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @ConditionalOnMissingBean
    @Bean
    public CustomCacheConfig customCacheConfig() {
        return new CustomCacheConfig() {
            @Override
            public long getDefaultExpiration() {
                return 3600;
            }

            @Override
            public Map<String, Long> getCustomCacheConfigs() {
                return Collections.emptyMap();
            }
        };
    }

}
