package com.hb0730.boot.admin.config.cache;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
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
