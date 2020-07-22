package com.hb0730.boot.admin.cache.support.redis.lettuce;

import io.lettuce.core.AbstractRedisClient;
import io.lettuce.core.api.StatefulConnection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bing_huang
 * @date 2020/07/22 8:19
 * @since V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisLettuceCacheConfig {
    private AbstractRedisClient redisClient;

    private StatefulConnection<byte[], byte[]> connection;

    private long asyncTimeout;

    public RedisLettuceCacheConfig(AbstractRedisClient redisClient, StatefulConnection<byte[], byte[]> connection) {
        this.redisClient = redisClient;
        this.connection = connection;
    }
}
