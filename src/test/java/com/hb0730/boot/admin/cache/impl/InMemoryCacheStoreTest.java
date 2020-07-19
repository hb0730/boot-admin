package com.hb0730.boot.admin.cache.impl;

import com.hb0730.boot.admin.cache.CacheWrapper;
import org.junit.Test;

import java.util.Optional;

public class InMemoryCacheStoreTest {
    InMemoryCacheStore<String, String> cache = new InMemoryCacheStore<>();

    @Test
    public void getInternal() {

    }

    @Test
    public void putInternal() {
        cache.put("test", "test");
        Optional<CacheWrapper<String>> test = cache.getInternal("test");

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
