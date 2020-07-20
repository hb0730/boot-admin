package com.hb0730.boot.admin.cache.impl;

import com.hb0730.boot.admin.cache.Cache;
import com.hb0730.boot.admin.cache.CacheWrapper;
import com.hb0730.boot.admin.utils.lang.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 缓存抽象
 *
 * @author bing_huang
 * @date 2020/07/18 13:23
 * @since V1.0
 */
public abstract class AbstractCache<K, V> implements Cache<K, V> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCache.class);

    /**
     * 根据key获取缓存包装
     *
     * @param key 缓存key 不为null
     * @return cache wrapper
     */
    @Nonnull
    abstract Optional<CacheWrapper<V>> getInternal(@Nonnull K key);

    /**
     * 设置缓存
     *
     * @param key          key不为null
     * @param cacheWrapper cache wrapper 不为null
     */
    abstract void putInternal(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper);

    /**
     * 设置缓存 ,key 如果不存在设置缓存,
     *
     * @param key          缓存key,不为null
     * @param cacheWrapper cache wrapper,不为null
     * @return true:缓存key不存在并已重新设置;false:缓存前key存在;null:其他原因
     */
    abstract Boolean putInternalIfAbsent(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper);


    @Nonnull
    @Override
    public Optional<V> get(@Nonnull K key) {
        Assert.notNull(key, "Cache key must not be blank");
        return getInternal(key).map(wrapper -> {
            // Check expiration
            if (wrapper.getExpireAt() != null && wrapper.getExpireAt().before(DateUtils.now())) {
                // Expired then delete it
                LOGGER.warn("Cache key: [{}] has been expired", key);

                // Delete the key
                delete(key);

                // Return null
                return null;
            }
            return wrapper.getData();
        });
    }

    @Override
    public void put(@Nonnull K key, @Nonnull V value, long timeout, @Nonnull TimeUnit timeUnit) {
        this.putInternal(key, this.buildCacheWrapper(value, timeout, timeUnit));
    }

    @Override
    public Boolean putIfAbsent(@Nonnull K key, @Nonnull V value, long timeout, @Nonnull TimeUnit timeUnit) {
        return this.putInternalIfAbsent(key, this.buildCacheWrapper(value, timeout, timeUnit));
    }

    @Override
    public void put(@Nonnull K key, @Nonnull V value) {
        this.putInternal(key, this.buildCacheWrapper(value, -1L, null));
    }

    /**
     * 构建cache wrapper
     *
     * @param value    cache value
     * @param timeout  cache time out  不能小于1
     * @param timeUnit Time out type
     * @return cache wrapper
     */
    private CacheWrapper<V> buildCacheWrapper(@Nonnull V value, Long timeout, @Nullable TimeUnit timeUnit) {
        Assert.notNull(value, "cache value must not be null");
        Assert.isTrue(timeout >= -1, "cache expiration timout must not be less than 1");
        Date now = DateUtils.now();
        Date expireAt = null;
        if (timeout > 0 && timeUnit != null) {
            expireAt = DateUtils.add(now, timeout, timeUnit);
        }

        // Build cache wrapper
        CacheWrapper<V> cacheWrapper = new CacheWrapper<>();
        cacheWrapper.setCreateAt(now);
        cacheWrapper.setExpireAt(expireAt);
        cacheWrapper.setData(value);

        return cacheWrapper;
    }
}
