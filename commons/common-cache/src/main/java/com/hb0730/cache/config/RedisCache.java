package com.hb0730.cache.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import com.google.common.collect.Maps;
import com.hb0730.cache.core.ICache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "boot.admin.cache.enabled", havingValue = "true", matchIfMissing = true)
public class RedisCache implements ICache {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Set<String> scanKeys(String pattern) {
        Set<String> keys = new HashSet<>();
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().match(pattern).count(1000).build());
            while (cursor.hasNext()) {
                keys.add(new String(cursor.next()));
            }
            return null;
        });
        return keys;
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void delete(Set<String> keys) {
        redisTemplate.delete(keys);
    }

    @Override
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    @Override
    public long getExpire(String key) {
        Long expire = redisTemplate.getExpire(key);
        return expire == null ? 0 : expire;
    }

    @Override
    public boolean expire(String key, long timeout) {
        Boolean res = redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(res);
    }

    @Override
    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean setObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    @Override
    public boolean setObjectIfAbsent(String key, Object value) {
        Boolean res = redisTemplate.opsForValue().setIfAbsent(key, value);
        return Boolean.TRUE.equals(res);
    }

    @Override
    public String getString(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        if (value != null) {
            return value.toString();
        }
        return null;
    }

    @Override
    public boolean setString(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    @Override
    public boolean setStringIfAbsent(String key, String value) {
        Boolean res = redisTemplate.opsForValue().setIfAbsent(key, value);
        return Boolean.TRUE.equals(res);
    }

    @Override
    public long incr(String key, long delta) {
        Assert.isTrue(delta > 0, "递增因子必须大于0");
        Long res = redisTemplate.opsForValue().increment(key, delta);
        return res == null ? 0 : res;
    }

    @Override
    public long decr(String key, long delta) {
        Assert.isTrue(delta > 0, "递减因子必须大于0");
        Long res = redisTemplate.opsForValue().increment(key, -delta);
        return res == null ? 0 : res;
    }

    @Override
    public String hGet(String key, String item) {
        Object object = redisTemplate.opsForHash().get(key, item);
        if (object != null) {
            return object.toString();
        }
        return null;
    }

    @Override
    public List<String> hmget(String key, List<String> items) {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        if (CollectionUtil.isEmpty(entries)) {
            return null;
        }
        List<String> result = new ArrayList<>();
        items.forEach(item -> {
            Object value = entries.get(item);
            String _value = Convert.toStr(value, "");
            result.add(_value);
        });
        return result;
    }

    @Override
    public Map<String, String> hValues(String key) {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        if (CollectionUtil.isEmpty(entries)) {
            return null;
        }
        Map<String, String> result = Maps.newHashMap();
        entries.forEach((k, v) -> result.put(k.toString(), Convert.toStr(v, "")));
        return result;
    }

    @Override
    public boolean hmset(String key, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(key, map);
        return true;
    }

    @Override
    public boolean hset(String key, String item, String value) {
        redisTemplate.opsForHash().put(key, item, value);
        return true;
    }

    @Override
    public boolean hdel(String key, String... item) {
        redisTemplate.opsForHash().delete(key, (Object[]) item);
        return true;
    }

    @Override
    public boolean hHasKey(String key, String item) {
        return Boolean.TRUE.equals(redisTemplate.opsForHash().hasKey(key, item));
    }

    @Override
    public double hincr(String key, String item, double by) {
        Assert.isTrue(by > 0, "递增因子必须大于0");
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    @Override
    public double hdecr(String key, String item, double by) {
        Assert.isTrue(by > 0, "递减因子必须大于0");
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    @Override
    public Set<String> sGet(String key) {
        Set<Object> members = redisTemplate.opsForSet().members(key);
        if (CollectionUtil.isEmpty(members)) {
            return null;
        }
        return members.stream().map(e -> Convert.toStr(e, "")).collect(
                Collectors.toSet()
        );
    }

    @Override
    public boolean sHasKey(String key, String value) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, value));
    }

    @Override
    public long sSet(String key, String... values) {
        Long res = redisTemplate.opsForSet().add(key, values);
        return res == null ? 0 : res;
    }

    @Override
    public long sSize(String key) {
        Long res = redisTemplate.opsForSet().size(key);
        return res == null ? 0 : res;
    }

    @Override
    public long sRemove(String key, String... values) {
        Long res = redisTemplate.opsForSet().remove(key, (Object[]) values);
        return res == null ? 0 : res;
    }

    @Override
    public List<String> lGet(String key, long start, long end) {
        List<Object> range = redisTemplate.opsForList().range(key, start, end);
        if (CollectionUtil.isEmpty(range)) {
            return null;
        }
        return range.stream().map(e -> Convert.toStr(e, "")).collect(
                Collectors.toList()
        );
    }

    @Override
    public long lSize(String key) {
        Long res = redisTemplate.opsForList().size(key);
        return res == null ? 0 : res;
    }

    @Override
    public String lGetIndex(String key, long index) {
        Object value = redisTemplate.opsForList().index(key, index);
        if (value != null) {
            return value.toString();
        }
        return null;
    }

    @Override
    public boolean lSet(String key, String value) {
        redisTemplate.opsForList().rightPush(key, value);
        return true;
    }

    @Override
    public boolean lSet(String key, List<String> values) {
        redisTemplate.opsForList().rightPushAll(key, values);
        return true;
    }

    @Override
    public boolean lUpdateIndex(String key, long index, String value) {
        redisTemplate.opsForList().set(key, index, value);
        return true;
    }

    @Override
    public long lRemove(String key, long count, String value) {
        Long res = redisTemplate.opsForList().remove(key, count, value);
        return res == null ? 0 : res;
    }
}