package com.hb0730.boot.admin.config.cache;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 缓存
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/11
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class BootAdminCache {
    private final BootAdminProperties cacheProperties;
    private final StringRedisTemplate redisTemplate;

    /**
     * 正规化缓存KEY,按照<code>:</code>进行拼接
     *
     * @param key key
     * @return <code>"prefix:key"</code>
     */
    public String normaliz(String key) {
        if (StrUtil.isBlank(key)) {
            return null;
        }
        return cacheProperties.getCache().getPrefix() + ":" + key;
    }

    /**
     * 是否存在
     *
     * @param key .
     * @return .
     */
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(normaliz(key)));
    }

    /**
     * 获取所有keys
     *
     * @param key .
     * @return .
     */
    public Set<String> getKeys(String key) {
        String normalizKey = normaliz(key);
        return Optional.ofNullable(redisTemplate.keys(normalizKey))
                .orElse(CollUtil.newHashSet());
    }

    /**
     * 设置过期时间，单位 秒/s
     *
     * @param key     key: prefix+key
     * @param timeout timeout 单位 秒/s
     * @return .
     */
    public boolean expire(final String key, long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置过期时间
     *
     * @param key     key,自动追加前缀{@link #normaliz(String)}
     * @param timeout timeout
     * @param unit    time unit
     * @return .
     */
    public boolean expire(final String key, long timeout, TimeUnit unit) {
        String _key = normaliz(key);
        Boolean _res = this.redisTemplate.expire(_key, timeout, unit);
        return Boolean.TRUE.equals(_res);
    }

    /**
     * 获取过期时间,单位 秒/s
     *
     * @param key key,自动追加前缀{@link #normaliz(String)}
     * @return timeout
     */
    public long getExpire(final String key) {
        return getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 获取剩下过期时间
     *
     * @param key  key,自动追加前缀{@link #normaliz(String)}
     * @param unit time unit
     * @return timeout
     */
    public long getExpire(final String key, TimeUnit unit) {
        String _key = normaliz(key);
        return Optional.ofNullable(this.redisTemplate.getExpire(_key, unit)).orElse(0L);
    }

    /**
     * 设置永久缓存
     *
     * @param key   key,自动追加前缀{@link #normaliz(String)}
     * @param value .
     * @return .
     */
    public boolean set(final String key, String value) {
        return set(key, value, -1);
    }

    /**
     * 设置缓存,单位时间/秒
     *
     * @param key     key,自动追加前缀{@link #normaliz(String)}
     * @param value   .
     * @param timeout timeout,如果为-l,则永久缓存
     * @return .
     */
    public boolean set(final String key, String value, long timeout) {
        return set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存
     *
     * @param key     key,自动追加前缀{@link #normaliz(String)}
     * @param value   .
     * @param timeout timeout,如果为-l,则永久缓存
     * @param unit    time unit
     * @return .
     */
    public boolean set(final String key, String value, long timeout, TimeUnit unit) {
        if (StrUtil.isBlank(key)) {
            log.warn("【boot-admin-cache】 set cache value warn, key missing");
            return false;
        }
        String _key = normaliz(key);
        log.debug("【boot-admin-cache】set cache value : key: {}, value: {},timeout: {}", _key, value, timeout);
        if (timeout == -1) {
            this.redisTemplate.opsForValue().set(_key, value);
        } else {
            this.redisTemplate.opsForValue().set(_key, value, timeout, unit);
        }
        return Boolean.TRUE;
    }

    /**
     * 获取缓存
     *
     * @param key,自动追加前缀{@link #normaliz(String)}
     * @return .
     */
    @Nonnull
    public Optional<String> getStr(final String key) {
        String _key = normaliz(key);
        return Optional.ofNullable(this.redisTemplate.opsForValue().get(_key));
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    @Nonnull
    public Optional<Map<String, String>> hmget(final String key) {
        String _key = normaliz(key);
        Map<Object, Object> entries = this.redisTemplate.opsForHash().entries(_key);
        return Optional.of(entries).map(Map::entrySet).map(entries1 -> {
            Map<String, String> map = new HashMap<>(entries1.size());
            entries1.forEach(entry -> map.put(entry.getKey().toString(), entry.getValue().toString()));
            return map;
        });
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key .
     * @return .
     */
    @Nonnull
    public Optional<Set<String>> sGet(final String key) {
        String _key = normaliz(key);
        return Optional.ofNullable(this.redisTemplate.opsForSet().members(_key));
    }

    /**
     * 获取list缓存的内容,默认 0 到 -1
     *
     * @param key .
     * @return .
     */
    @Nonnull
    public Optional<List<String>> lGet(final String key) {
        return lGet(key, 0, -1);
    }

    /**
     * 获取list缓存的内容， 0 到 -1代表所有值
     *
     * @param key   .
     * @param start 开始
     * @param end   结束
     * @return .
     */
    public Optional<List<String>> lGet(final String key, long start, long end) {
        String _key = normaliz(key);
        return Optional.ofNullable(this.redisTemplate.opsForList().range(_key, start, end));
    }

    /**
     * 批量删除
     *
     * @param keys .
     * @return .
     */
    public boolean del(Collection<String> keys) {
        if (CollUtil.isEmpty(keys)) {
            return Boolean.FALSE;
        }
        keys.forEach(this::del);
        return Boolean.TRUE;
    }

    /**
     * 删除缓存
     *
     * @param key,自动追加前缀{@link #normaliz(String)}
     * @return .
     */
    public boolean del(final String key) {
        String _key = normaliz(key);
        return Boolean.TRUE.equals(this.redisTemplate.delete(_key));
    }
}
