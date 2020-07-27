package com.hb0730.boot.admin.cache.impl.remote;

import com.hb0730.boot.admin.cache.CacheWrapper;
import com.hb0730.boot.admin.cache.support.redis.lettuce.LettuceConnectionManager;
import com.hb0730.boot.admin.cache.support.redis.lettuce.RedisLettuceCacheConfig;
import com.hb0730.boot.admin.cache.support.serial.Serializer;
import io.lettuce.core.AbstractRedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.async.RedisKeyAsyncCommands;
import io.lettuce.core.api.async.RedisStringAsyncCommands;
import io.lettuce.core.api.sync.RedisStringCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author bing_huang
 * @date 2020/07/22 7:45
 * @since V1.0
 */
public class RedisLettuceCacheStore<K, V> extends AbstractRemoteCache<K, V> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLettuceCacheStore.class);
    private final Long asyncTimeout;
    private final Serializer serializer;

    private final RedisStringCommands<byte[], byte[]> stringCommands;
    private final RedisStringAsyncCommands<byte[], byte[]> stringAsyncCommands;
    private final RedisKeyAsyncCommands<byte[], byte[]> keyAsyncCommands;

    @SuppressWarnings({"unchecked"})
    public RedisLettuceCacheStore(RedisLettuceCacheConfig<K, V> config) {
        super(config);
        this.serializer = config.getSerializer();
        Assert.notNull(config.getRedisClient(), "RedisClient is required");
        AbstractRedisClient client = config.getRedisClient();
        this.asyncTimeout = config.getAsyncTimeout();
        LettuceConnectionManager lettuceConnectionManager = LettuceConnectionManager.defaultManager();
        lettuceConnectionManager.init(client, config.getConnection());
        stringCommands = (RedisStringCommands<byte[], byte[]>) lettuceConnectionManager.commands(client);
        stringAsyncCommands = (RedisStringAsyncCommands<byte[], byte[]>) lettuceConnectionManager.asyncCommands(client);
        keyAsyncCommands = (RedisKeyAsyncCommands<byte[], byte[]>) stringAsyncCommands;
    }

    @Nonnull
    @Override
    @SuppressWarnings({"unchecked"})
    protected Optional<CacheWrapper<V>> getInternal(@Nonnull K key) {
        Assert.notNull(key, "Cache key must not be null");
        try {
            byte[] bytes = buildKey(key);
            CacheWrapper<V> cache;
            if (asyncTimeout == null || 0 == asyncTimeout) {
                byte[] valueBytes = stringCommands.get(bytes);
                cache = (CacheWrapper<V>) serializer.deserialize(valueBytes);
            } else {
                RedisFuture<byte[]> future = stringAsyncCommands.get(bytes);
                byte[] valueBytes = future.get(asyncTimeout, TimeUnit.MILLISECONDS);
                cache = (CacheWrapper<V>) serializer.deserialize(valueBytes);
            }
            LOGGER.debug("get success,[{}],[{}]", key, cache);
            return Optional.ofNullable(cache);
        } catch (Exception e) {
            LOGGER.error("get error the key [{}]", key);
        }
        return Optional.empty();
    }

    @Override
    protected void putInternal(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        Assert.notNull(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");
        try {
            byte[] newkey = buildKey(key);
            RedisFuture<String> rest = stringAsyncCommands.psetex(newkey,
                    expireTime(cacheWrapper.getCreateAt(), cacheWrapper.getExpireAt()),
                    serializer.serialize(cacheWrapper));
            rest.handle((rt, ex) -> {
                if (ex != null) {
                    LOGGER.warn("put error ,", ex);
                } else {
                    LOGGER.debug("put success {}", rt);
                }
                return rt;
            });
        } catch (Exception e) {
            LOGGER.error("put error");
        }
    }

    @Override
    protected Boolean putInternalIfAbsent(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        Assert.notNull(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");
        try {
            byte[] bytes = buildKey(key);
            long timeout = expireTime(cacheWrapper.getCreateAt(), cacheWrapper.getExpireAt());
            byte[] valueBytes = serializer.serialize(cacheWrapper);
            RedisFuture<String> rest = stringAsyncCommands.set(bytes, valueBytes, SetArgs.Builder.nx().px(timeout));
            rest.handle((rt, ex) -> {
                if (ex != null) {
                    LOGGER.warn("put error ,", ex);
                } else {
                    LOGGER.debug("put success {}", rt);
                }
                return rt;
            });
            return null;
        } catch (Exception e) {
            LOGGER.error("put error [{}]", key);
        }
        return false;
    }

    @Override
    public void delete(@Nonnull K key) {
        Assert.notNull(key, "Cache key must not be blank");
        try {
            keyAsyncCommands.del(buildKey(key));
            LOGGER.debug("delete success");
        } catch (Exception e) {
            LOGGER.error("remove error [{}]", key);
        }
    }
}
