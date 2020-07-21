package com.hb0730.boot.admin.cache.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.hb0730.boot.admin.cache.CacheWrapper;
import com.hb0730.boot.admin.cache.support.redis.springdata.RedisSpringDataCacheConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.types.Expiration;
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
import java.util.concurrent.TimeUnit;

/**
 * spring redis impl
 *
 * @author bing_huang
 * @date 2020/07/20 7:41
 * @since V1.0
 */
public class RedisSpringDataCache<K, V> extends AbstractCache<K, V> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSpringDataCache.class);
    private final RedisConnectionFactory connectionFactory;

    public RedisSpringDataCache(RedisSpringDataCacheConfig config) {
        this.connectionFactory = config.getConnectionFactory();
        Assert.notNull(connectionFactory, "connectionFactory is required");
    }

    public RedisSpringDataCache(RedisConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        Assert.notNull(connectionFactory, "connectionFactory is required");
    }

    @Nonnull
    @Override
    @SuppressWarnings({"unchecked"})
    Optional<CacheWrapper<V>> getInternal(@Nonnull K key) {
        RedisConnection connection = null;
        try {
            connection = connectionFactory.getConnection();
            byte[] newKey = buildKey(key);
            byte[] resultBytes = connection.get(newKey);
            if (resultBytes != null) {
                CacheWrapper<V> result = (CacheWrapper<V>) ObjectUtil.deserialize(resultBytes);
                LOGGER.debug("get success key:[{}],result:[{}]", key, result);
                return Optional.ofNullable(result);
            }
            return Optional.empty();
        } catch (Exception e) {
            LOGGER.error("get error key [{}]", key);
        } finally {
            closeConnection(connection);
        }

        return Optional.empty();
    }

    @Override
    void putInternal(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        Assert.notNull(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");
        RedisConnection connection = null;
        try {
            connection = connectionFactory.getConnection();
            byte[] keyByte = buildKey(key);
            byte[] valueBytes = ObjectUtil.serialize(cacheWrapper);
            long expireAt = DateUtil.between(cacheWrapper.getCreateAt(), cacheWrapper.getExpireAt(), DateUnit.MS);
            connection.pSetEx(keyByte, expireAt, valueBytes);
            LOGGER.debug("put success then key [{}]", key);
        } catch (Exception e) {
            LOGGER.error("put error", e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    Boolean putInternalIfAbsent(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        Assert.notNull(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");
        RedisConnection connection = null;
        try {
            connection = connectionFactory.getConnection();
            byte[] newkey = buildKey(key);
            byte[] valueByte = ObjectUtil.serialize(cacheWrapper);
            long expireAt = DateUtil.between(cacheWrapper.getCreateAt(), cacheWrapper.getExpireAt(), DateUnit.MS);
            Boolean result = connection.set(newkey, valueByte,
                    Expiration.from(expireAt, TimeUnit.MILLISECONDS),
                    RedisStringCommands.SetOption.ifAbsent());
            LOGGER.debug("put_is_absent success,then key [{}] result:[{}]", key, result);
            return result;
        } catch (Exception e) {
            LOGGER.error("put_if_absent error", e);
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    @Override
    public void delete(@Nonnull K key) {
        Assert.notNull(key, "Cache key must not be null");
        RedisConnection connection = null;
        try {
            byte[] keyByte = buildKey(key);
            connection = connectionFactory.getConnection();
            connection.del(keyByte);
            LOGGER.debug("delete success then key [{}]", key);
        } catch (Exception e) {
            LOGGER.error("delete error", e);
        } finally {
            closeConnection(connection);
        }
    }


    protected byte[] buildKey(K key) throws IOException {
        Assert.notNull(key, "key must not null");
        byte[] keyBytesWithOutPrefix = null;
        if (key instanceof String) {
            keyBytesWithOutPrefix = key.toString().getBytes(StandardCharsets.UTF_8);
        } else if (key instanceof byte[]) {
            keyBytesWithOutPrefix = (byte[]) key;
        } else if (key instanceof Number) {
            keyBytesWithOutPrefix = (((Object) key).getClass().getSimpleName() + key).getBytes(StandardCharsets.UTF_8);
        } else if (key instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss,SSS");
            keyBytesWithOutPrefix = (((Object) key).getClass().getSimpleName() + sdf.format(key)).getBytes();
        } else if (key instanceof Boolean) {
            keyBytesWithOutPrefix = key.toString().getBytes(StandardCharsets.UTF_8);
        } else if (key instanceof Serializable) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            os.writeObject(key);
            os.close();
            bos.close();
            keyBytesWithOutPrefix = bos.toByteArray();
        } else {
            Assert.isTrue(true, "can't convert key of class:" + ((Object) key).getClass());
        }
        return keyBytesWithOutPrefix;
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
}
