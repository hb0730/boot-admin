package com.hb0730.boot.admin.cache.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class RedisSpringDataCacheTest {
    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Test
    public void put() {
        AbstractCache<String, String> cache = new RedisSpringDataCache<>(connectionFactory);
        cache.put("test", "test");
    }

    @Test
    public void get() {
        AbstractCache<String, String> cache = new RedisSpringDataCache<String, String>(connectionFactory);
        Optional<String> test = cache.get("test");
        System.out.println(test.orElseGet(() -> "为空"));
    }

    @Test
    public void putTimout() {
        AbstractCache<String, String> cache = new RedisSpringDataCache<String, String>(connectionFactory);
        cache.putIfAbsent("test", "test2", 200L, TimeUnit.SECONDS);
    }

    @Test
    public void delete() {
        AbstractCache<String, String> cache = new RedisSpringDataCache<String, String>(connectionFactory);
        cache.delete("test");
    }
}
