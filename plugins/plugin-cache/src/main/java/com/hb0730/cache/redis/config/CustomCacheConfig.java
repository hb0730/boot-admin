package com.hb0730.cache.redis.config;

import java.util.Map;

/**
 * 自定义缓存配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/23
 */
public interface CustomCacheConfig {

    /**
     * 默认过期时间，单位秒
     *
     * @return 过期时间
     */
    long getDefaultExpiration();

    /**
     * 自定义缓存配置
     *
     * @return 缓存配置
     */
    Map<String, Long> getCustomCacheConfigs();
}
