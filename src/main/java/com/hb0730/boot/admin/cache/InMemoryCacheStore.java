package com.hb0730.boot.admin.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import javax.annotation.PreDestroy;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
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
    private final static long PERIOD = 60 * 1000;

    /**
     * Cache container.
     */
    private final ConcurrentHashMap<K, CacheWrapper<V>> CACHE_CONTAINER = new ConcurrentHashMap<>();
    /**
     * Lock.
     */
    private final Lock lock = new ReentrantLock();

    private final Timer timer;

    public InMemoryCacheStore() {
        // Run a cache store cleaner
        this.timer = new Timer();
        timer.scheduleAtFixedRate(new CacheExpiryCleaner(), 0, PERIOD);
    }

    @Nonnull
    @Override
    public Optional<CacheWrapper<V>> getInternal(@Nonnull K key) {
        Assert.notNull(key, "Cache key must not be blank");
        return Optional.ofNullable(CACHE_CONTAINER.get(key));
    }

    @Override
    public void putInternal(@Nonnull K key, @Nonnull CacheWrapper<V> cacheWrapper) {
        Assert.notNull(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");

        // Put the cache wrapper
        CACHE_CONTAINER.put(key, cacheWrapper);

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
        CACHE_CONTAINER.remove(key);
    }


    @PreDestroy
    public void preDestroy() {
        timer.cancel();
        clear();
    }

    private void clear() {
        CACHE_CONTAINER.clear();
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
            CACHE_CONTAINER.keySet().forEach(key -> {
                if (InMemoryCacheStore.this.get(key).isEmpty()) {
                    LOGGER.debug("Deleted the cache: [{}] for expiration", key);
                }
            });
        }
    }

}
