package com.hb0730.boot.admin.cache.support.redis.lettuce;

import com.alicp.jetcache.CacheConfigException;
import io.lettuce.core.AbstractRedisClient;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulConnection;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.sentinel.api.StatefulRedisSentinelConnection;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author bing_huang
 * @date 2020/07/22 8:16
 * @since V1.0
 */
public class LettuceConnectionManager {
    @SuppressWarnings({"rawtypes"})
    private static class LettuceObjects {
        private StatefulConnection connection;
        private Object commands;
        private Object asyncCommands;
        private Object reactiveCommands;
    }

    private static final LettuceConnectionManager DEFAULT_MANAGER = new LettuceConnectionManager();
    @SuppressWarnings({"rawtypes", "unchecked"})
    private final Map<AbstractRedisClient, LettuceObjects> map = Collections.synchronizedMap(new WeakHashMap());

    private LettuceConnectionManager() {
    }

    public static LettuceConnectionManager defaultManager() {
        return DEFAULT_MANAGER;
    }

    private LettuceObjects getLettuceObjectsFromMap(AbstractRedisClient redisClient) {
        LettuceObjects lo = map.get(redisClient);
        Assert.notNull(lo, "LettuceObjects is not initialized");
        return lo;
    }

    @SuppressWarnings({"rawtypes"})
    public void init(AbstractRedisClient redisClient, StatefulConnection connection) {
        map.computeIfAbsent(redisClient, key -> {
            LettuceObjects lo = new LettuceObjects();
            lo.connection = connection;
            return lo;
        });
    }

    @SuppressWarnings({"rawtypes"})
    public StatefulConnection connection(AbstractRedisClient redisClient) {
        LettuceObjects lo = getLettuceObjectsFromMap(redisClient);
        if (lo.connection == null) {
            if (redisClient instanceof RedisClient) {
                lo.connection = ((RedisClient) redisClient).connect(new LettuceCodec());
            } else if (redisClient instanceof RedisClusterClient) {
                lo.connection = ((RedisClusterClient) redisClient).connect(new LettuceCodec());
            } else {
                throw new CacheConfigException("type " + redisClient.getClass() + " is not supported");
            }
        }
        return lo.connection;
    }

    @SuppressWarnings({"rawtypes"})
    public Object commands(AbstractRedisClient redisClient) {
        connection(redisClient);
        LettuceObjects lo = getLettuceObjectsFromMap(redisClient);
        if (lo.commands == null) {
            if (lo.connection instanceof StatefulRedisConnection) {
                lo.commands = ((StatefulRedisConnection) lo.connection).sync();
            } else if (lo.connection instanceof StatefulRedisClusterConnection) {
                lo.commands = ((StatefulRedisClusterConnection) lo.connection).sync();
            } else if (lo.connection instanceof StatefulRedisSentinelConnection) {
                lo.commands = ((StatefulRedisSentinelConnection) lo.connection).sync();
            } else {
                throw new CacheConfigException("type " + lo.connection.getClass() + " is not supported");
            }
        }
        return lo.commands;
    }

    @SuppressWarnings({"rawtypes"})
    public Object asyncCommands(AbstractRedisClient redisClient) {
        connection(redisClient);
        LettuceObjects lo = getLettuceObjectsFromMap(redisClient);
        if (lo.asyncCommands == null) {
            if (lo.connection instanceof StatefulRedisConnection) {
                lo.asyncCommands = ((StatefulRedisConnection) lo.connection).async();
            } else if (lo.connection instanceof StatefulRedisClusterConnection) {
                lo.asyncCommands = ((StatefulRedisClusterConnection) lo.connection).async();
            } else if (lo.connection instanceof StatefulRedisSentinelConnection) {
                lo.asyncCommands = ((StatefulRedisSentinelConnection) lo.connection).async();
            } else {
                throw new CacheConfigException("type " + lo.connection.getClass() + " is not supported");
            }
        }
        return lo.asyncCommands;
    }

    @SuppressWarnings({"rawtypes"})
    public Object reactiveCommands(AbstractRedisClient redisClient) {
        connection(redisClient);
        LettuceObjects lo = getLettuceObjectsFromMap(redisClient);
        if (lo.reactiveCommands == null) {
            if (lo.connection instanceof StatefulRedisConnection) {
                lo.reactiveCommands = ((StatefulRedisConnection) lo.connection).reactive();
            } else if (lo.connection instanceof StatefulRedisClusterConnection) {
                lo.reactiveCommands = ((StatefulRedisClusterConnection) lo.connection).reactive();
            } else if (lo.connection instanceof StatefulRedisSentinelConnection) {
                lo.reactiveCommands = ((StatefulRedisSentinelConnection) lo.connection).reactive();
            } else {
                throw new CacheConfigException("type " + lo.connection.getClass() + " is not supported");
            }
        }
        return lo.reactiveCommands;
    }

    public void removeAndClose(AbstractRedisClient redisClient) {
        LettuceObjects lo = (LettuceObjects) map.remove(redisClient);
        if (lo == null) {
            return;
        }
        if (lo.connection != null) {
            lo.connection.close();
        }
        redisClient.shutdown();
    }
}
