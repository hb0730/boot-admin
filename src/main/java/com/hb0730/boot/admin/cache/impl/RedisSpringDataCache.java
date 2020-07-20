package com.hb0730.boot.admin.cache.impl;

import com.hb0730.boot.admin.cache.CacheWrapper;
import com.hb0730.boot.admin.cache.support.redis.springdata.RedisSpringDataCacheConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * @author bing_huang
 * @date 2020/07/20 7:41
 * @since V1.0
 */
public class RedisSpringDataCache<K, V> extends AbstractCache<K, V> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSpringDataCache.class);
    private final RedisConnectionFactory connectionFactory;
    private final RedisSpringDataCacheConfig config;

    public RedisSpringDataCache(RedisSpringDataCacheConfig config) {
        this.config = config;
        this.connectionFactory = config.getConnectionFactory();
        Assert.notNull(connectionFactory, "connectionFactory is required");
    }

    @Nonnull
    @Override
    Optional<CacheWrapper<V>> getInternal(@Nonnull K key) {
        RedisConnection connection = null;
        try {
            connection = connectionFactory.getConnection();
            byte[] newKey = buildKey(key);
            byte[] resultBytes = connection.get(newKey);
            if (resultBytes != null) {

            }
        } catch (Exception e) {

        }

        return Optional.empty();
    }

    @Override
    void putInternal(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        Assert.notNull(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");
        RedisConnection connection = null;
        try {
            byte[] keyByte = buildKey(key);
            connection = connectionFactory.getConnection();
            // 序列化
            connection.pSetEx(keyByte, cacheWrapper.getExpireAt().getTime(), null);
        } catch (Exception e) {
            LOGGER.error("put error", e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    Boolean putInternalIfAbsent(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        return null;
    }

    @Override
    public void delete(@Nonnull K key) {
        Assert.notNull(key, "Cache key must not be null");
        RedisConnection connection = null;
        try {
            byte[] keyByte = buildKey(key);
            connection = connectionFactory.getConnection();
            connection.del(keyByte);
        } catch (Exception e) {
            LOGGER.error("delete error", e);
        } finally {
            closeConnection(connection);
        }
    }

    private void closeConnection(RedisConnection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            LOGGER.error("RedisConnection close fail: {}, {}", e.getMessage(), e.getClass().getName());
        }
    }

    protected byte[] buildKey(K key) throws IOException {
        Assert.notNull(key, "key must not null");
        Object newKey = key;
        byte[] keyBytesWithOutPrefix = null;
        if (newKey instanceof String) {
            keyBytesWithOutPrefix = newKey.toString().getBytes(StandardCharsets.UTF_8);
        } else if (newKey instanceof byte[]) {
            keyBytesWithOutPrefix = (byte[]) newKey;
        } else if (newKey instanceof Number) {
            keyBytesWithOutPrefix = (newKey.getClass().getSimpleName() + newKey).getBytes(StandardCharsets.UTF_8);
        } else if (newKey instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss,SSS");
            keyBytesWithOutPrefix = (newKey.getClass().getSimpleName() + sdf.format(newKey)).getBytes();
        } else if (newKey instanceof Boolean) {
            keyBytesWithOutPrefix = newKey.toString().getBytes(StandardCharsets.UTF_8);
        } else if (newKey instanceof Serializable) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            os.writeObject(newKey);
            os.close();
            bos.close();
            keyBytesWithOutPrefix = bos.toByteArray();
        } else {
            Assert.isTrue(true, "can't convert key of class:" + newKey.getClass());
        }


        return keyBytesWithOutPrefix;
    }
}
