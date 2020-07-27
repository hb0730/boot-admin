package com.hb0730.boot.admin.cache.impl.remote;

import com.hb0730.boot.admin.cache.CacheWrapper;
import com.hb0730.boot.admin.cache.support.redis.springdata.RedisSpringDataCacheConfig;
import com.hb0730.boot.admin.cache.support.serial.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * spring redis impl
 *
 * @author bing_huang
 * @date 2020/07/20 7:41
 * @since V1.0
 */
public class RedisSpringDataCache<K, V> extends AbstractRemoteCache<K, V> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSpringDataCache.class);
    private final RedisConnectionFactory connectionFactory;
    private final Serializer serializer;

    public RedisSpringDataCache(RedisSpringDataCacheConfig<K, V> config) {
        super(config);
        this.connectionFactory = config.getConnectionFactory();
        this.serializer = config.getSerializer();
        Assert.notNull(connectionFactory, "connectionFactory is required");
    }

    @Nonnull
    @Override
    @SuppressWarnings({"unchecked"})
    protected Optional<CacheWrapper<V>> getInternal(@Nonnull K key) {
        Assert.notNull(key, "Cache key must not be null");
        RedisConnection connection = null;
        try {
            connection = connectionFactory.getConnection();
            byte[] newKey = buildKey(key);
            byte[] resultBytes = connection.get(newKey);
            if (resultBytes != null) {
                CacheWrapper<V> result = (CacheWrapper<V>) serializer.deserialize(resultBytes);
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
    protected void putInternal(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        Assert.notNull(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");
        RedisConnection connection = null;
        try {
            connection = connectionFactory.getConnection();
            byte[] keyByte = buildKey(key);
            byte[] valueBytes = serializer.serialize(cacheWrapper);
            assert valueBytes != null;
            long expireAt = expireTime(cacheWrapper.getCreateAt(), cacheWrapper.getExpireAt());
            connection.pSetEx(keyByte, expireAt, valueBytes);
            LOGGER.debug("put success then key [{}]", key);
        } catch (Exception e) {
            LOGGER.error("put error", e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    protected Boolean putInternalIfAbsent(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        Assert.notNull(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");
        RedisConnection connection = null;
        try {
            connection = connectionFactory.getConnection();
            byte[] newkey = buildKey(key);
            byte[] valueBytes = serializer.serialize(cacheWrapper);
            assert valueBytes != null;
            long expireAt = expireTime(cacheWrapper.getCreateAt(), cacheWrapper.getExpireAt());
            Boolean result = connection.set(newkey, valueBytes,
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
