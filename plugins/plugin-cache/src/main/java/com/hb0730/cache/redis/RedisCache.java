package com.hb0730.cache.redis;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import com.google.common.collect.Maps;
import com.hb0730.base.utils.JsonUtil;
import com.hb0730.cache.core.ICache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.KeyScanOptions;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * redis缓存
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/23
 */
@RequiredArgsConstructor
@Slf4j
public class RedisCache implements ICache {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Optional<Set<String>> scanKeys(String pattern) {
        // https://redis.io/docs/latest/commands/keys/
        // 注意：这里不要使用keys方法，会导致线程阻塞，生产环境慎用
        // Set<String> keys = redisTemplate.keys(pattern);
        Set<String> keys = new HashSet<>();
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            try (Cursor<byte[]> cursor = connection.keyCommands().scan(KeyScanOptions.scanOptions().match(pattern).count(1000).build())) {
                while (cursor.hasNext()) {
                    keys.add(new String(cursor.next()));
                }
            } catch (Exception e) {
                log.error("scan keys error", e);
            }
            return null;
        });
        return Optional.of(keys);
    }

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void del(Set<String> keys) {
        redisTemplate.delete(keys);
    }

    @Override
    public void delScan(String pattern) {
        Set<String> keys = scanKeys(pattern).orElse(new HashSet<>());
        if (CollectionUtil.isNotEmpty(keys)) {
            redisTemplate.delete(keys);
        }

    }

    @Override
    public boolean hasKey(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public long getExpire(String key) {
        Long expire = redisTemplate.getExpire(key);
        return expire == null ? 0 : expire;
    }

    @Override
    public boolean expire(String key, long expire) {
        try {
            if (expire <= 0) {
                return Boolean.TRUE.equals(redisTemplate.persist(key));
            }
            Boolean res = redisTemplate.expire(key, expire, TimeUnit.SECONDS);
            return Boolean.TRUE.equals(res);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Optional<Object> getObject(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }

    @Override
    public boolean setObject(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean setObject(String key, Object value, long expire, TimeUnit timeUnit) {
        try {
            if (expire <= 0) {
                redisTemplate.opsForValue().set(key, value);
                return true;
            }
            redisTemplate.opsForValue().set(key, value, expire, timeUnit);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }


    @Override
    public boolean setObjectIfAbsent(String key, Object value) {
        try {
            Boolean res = redisTemplate.opsForValue().setIfAbsent(key, value);
            return Boolean.TRUE.equals(res);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean setObjectIfAbsent(String key, Object value, long expire, TimeUnit timeUnit) {
        try {
            if (expire <= 0) {
                return setObjectIfAbsent(key, value);
            }
            Boolean res = redisTemplate.opsForValue().setIfAbsent(key, value, expire, timeUnit);
            return Boolean.TRUE.equals(res);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean setJson(String key, Object value) {
        try {
            String _value = JsonUtil.DEFAULT.toJson(value);
            redisTemplate.opsForValue().set(key, _value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean setJson(String key, Object value, long expire, TimeUnit timeUnit) {
        try {
            String _value = JsonUtil.DEFAULT.toJson(value);
            if (expire <= 0) {
                redisTemplate.opsForValue().set(key, _value);
                return true;
            }
            redisTemplate.opsForValue().set(key, _value, expire, timeUnit);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Optional<String> getString(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        return Optional.ofNullable(value == null ? null : value.toString());
    }

    @Override
    public Optional<List<String>> getString(Collection<String> keys, boolean ignore) {
        List<Object> values = redisTemplate.opsForValue().multiGet(keys);
        if (CollectionUtil.isEmpty(values)) {
            return Optional.empty();
        }
        return Optional.of(values.stream().map(e -> e == null ? null : e.toString()).collect(Collectors.toList()));
    }

    @Override
    public boolean setString(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean setString(String key, String value, long expire, TimeUnit timeUnit) {
        try {
            if (expire <= 0) {
                redisTemplate.opsForValue().set(key, value);
            }
            redisTemplate.opsForValue().set(key, value, expire, timeUnit);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }


    @Override
    public boolean setStringIfAbsent(String key, String value) {
        try {
            Boolean res = redisTemplate.opsForValue().setIfAbsent(key, value);
            return Boolean.TRUE.equals(res);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean setStringIfAbsent(String key, String value, long expire, TimeUnit timeUnit) {
        try {
            if (expire <= 0) {
                return setStringIfAbsent(key, value);
            }
            Boolean res = redisTemplate.opsForValue().setIfAbsent(key, value, expire, timeUnit);
            return Boolean.TRUE.equals(res);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
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
    public Optional<String> hGet(String key, String field) {
        Object value = redisTemplate.opsForHash().get(key, field);
        return Optional.ofNullable(value == null ? null : value.toString());
    }

    @Override
    public Optional<List<String>> hmget(String key, List<String> fields) {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        if (CollectionUtil.isEmpty(entries)) {
            return Optional.empty();
        }
        List<String> result = new ArrayList<>();
        fields.forEach(item -> {
            Object value = entries.get(item);
            String _value = Convert.toStr(value, "");
            result.add(_value);
        });
        return Optional.of(result);
    }

    @Override
    public Optional<Map<String, String>> hValues(String key) {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        if (CollectionUtil.isEmpty(entries)) {
            return Optional.empty();
        }
        Map<String, String> result = Maps.newHashMap();
        entries.forEach((k, v) -> result.put(k.toString(), Convert.toStr(v, "")));
        return Optional.of(result);
    }

    @Override
    public boolean hmset(String key, Map<String, String> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean hset(String key, String item, String value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
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
    public Optional<Set<String>> sGet(String key) {
        try {
            Set<Object> members = redisTemplate.opsForSet().members(key);
            if (CollectionUtil.isEmpty(members)) {
                return Optional.empty();
            }
            return Optional.of(members.stream().map(e -> Convert.toStr(e, "")).collect(
                    Collectors.toSet()
            ));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public boolean sHasKey(String key, String value) {
        try {
            return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, value));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public long sSet(String key, String... values) {
        try {
            Long res = redisTemplate.opsForSet().add(key, values);
            return res == null ? 0 : res;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    public long sSize(String key) {
        try {
            Long res = redisTemplate.opsForSet().size(key);
            return res == null ? 0 : res;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    public long sRemove(String key, String... values) {
        try {
            Long res = redisTemplate.opsForSet().remove(key, (Object[]) values);
            return res == null ? 0 : res;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    public List<String> randomMembers(String key, int size) {
        try {
            Set<Object> members = redisTemplate.opsForSet().distinctRandomMembers(key, size);
            if (CollectionUtil.isEmpty(members)) {
                return new ArrayList<>();
            }
            return members.stream().map(e -> Convert.toStr(e, "")).collect(Collectors.toList());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<List<String>> lGet(String key, long start, long end) {
        try {
            List<Object> range = redisTemplate.opsForList().range(key, start, end);
            if (CollectionUtil.isEmpty(range)) {
                return Optional.empty();
            }
            return Optional.of(range.stream().map(e -> Convert.toStr(e, "")).collect(
                    Collectors.toList()
            ));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public long lSize(String key) {
        try {
            Long res = redisTemplate.opsForList().size(key);
            return res == null ? 0 : res;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    public Optional<String> lGetIndex(String key, long index) {
        try {
            Object value = redisTemplate.opsForList().index(key, index);
            return Optional.ofNullable(value == null ? null : value.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public boolean lSet(String key, String value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean lSet(String key, List<String> values) {
        try {
            redisTemplate.opsForList().rightPushAll(key, values);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean lUpdateIndex(String key, long index, String value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public long lRemove(String key, long count, String value) {
        try {
            Long res = redisTemplate.opsForList().remove(key, count, value);
            return res == null ? 0 : res;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0;
        }
    }
}
