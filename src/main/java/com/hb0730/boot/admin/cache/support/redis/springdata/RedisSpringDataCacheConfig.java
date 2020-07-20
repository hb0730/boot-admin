package com.hb0730.boot.admin.cache.support.redis.springdata;

import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * @author bing_huang
 * @date 2020/07/20 7:40
 * @since V1.0
 */
public class RedisSpringDataCacheConfig {

    private RedisConnectionFactory connectionFactory;

    public RedisConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(RedisConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
}
