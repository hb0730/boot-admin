package com.hb0730.boot.admin.cache.impl;

import com.hb0730.boot.admin.cache.CacheWrapper;
import com.hb0730.boot.admin.cache.GlobalPruneTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TimerTask;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * in-memory cache store
 *
 * @author bing_huang
 * @date 2020/07/18 21:20
 * @since V1.0
 */
public class InMemoryCacheStore<K, V> extends AbstractCacheStore<K, V> {
    public static final Logger LOGGER = LoggerFactory.getLogger(InMemoryCacheStore.class);
    /**
     * Cleaner schedule period. (ms)
     */
    private final static long PERIOD = 5;

    /**
     * Cache container.
     */
    private final Map<K, CacheWrapper<V>> cache_container;
    /**
     * Lock.
     */
    private final Lock lock = new ReentrantLock();

    /**
     * 正在执行的定时任务
     */
    private final ScheduledFuture<?> pruneJobFuture;


    public InMemoryCacheStore() {
        this(new HashMap<>());
    }

    public InMemoryCacheStore(Map<K, CacheWrapper<V>> concurrentHashMap) {
        this.cache_container = concurrentHashMap;
        this.pruneJobFuture = GlobalPruneTimer.INSTANCE.schedule(new CacheExpiryCleaner(), PERIOD);

    }


    @Nonnull
    @Override
    public Optional<CacheWrapper<V>> getInternal(@Nonnull K key) {
        Assert.notNull(key, "Cache key must not be blank");
        return Optional.ofNullable(cache_container.get(key));
    }

    @Override
    public void putInternal(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        Assert.notNull(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");
        // Put the cache wrapper
        cache_container.put(key, cacheWrapper);

    }

    @Override
    public Boolean putInternalIfAbsent(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        Assert.notNull(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");

        lock.lock();
        try {
            // Get the value before
            Optional<V> valueOptional = get(key);

            if (valueOptional.isPresent()) {
                return false;
            }

            // Put the cache wrapper
            putInternal(key, cacheWrapper);
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void delete(@Nonnull K key) {
        Assert.notNull(key, "Cache key must not be blank");
        cache_container.remove(key);
    }


    @PreDestroy
    public void preDestroy() {
        if (null != pruneJobFuture) {
            pruneJobFuture.cancel(true);
        }
        clear();
    }

    private void clear() {
        cache_container.clear();
    }

    /**
     * Cache cleaner.
     *
     * @author johnniang
     * @date 03/28/19
     */
    private class CacheExpiryCleaner extends TimerTask {

        @Override
        public void run() {
            cache_container.keySet().forEach(key -> {
                if (InMemoryCacheStore.this.get(key).isEmpty()) {
                    LOGGER.debug("Deleted the cache: [{}] for expiration", key);
                }
            });
        }
    }

}
