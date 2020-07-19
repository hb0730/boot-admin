package com.hb0730.boot.admin.cache.impl;

import com.hb0730.boot.admin.cache.CacheWrapper;
import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class InMemoryCacheStoreTest {
    InMemoryCacheStore<String, String> cache = new InMemoryCacheStore<>();

    @Test
    public void getInternal() {

    }

    @Test
    public void putInternal() throws InterruptedException {
        cache.put("test", "test");
        Thread.sleep(1000L);
        Optional<CacheWrapper<String>> test = cache.getInternal("test");
        cache.put("test2", "test2", 60, TimeUnit.MILLISECONDS);
        Thread.sleep(1000L);
        test = cache.getInternal("test2");

    }

    @Test
    public void putInternalIfAbsent() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void preDestroy() {
    }
}
