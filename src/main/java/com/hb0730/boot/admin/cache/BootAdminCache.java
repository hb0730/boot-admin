package com.hb0730.boot.admin.cache;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Boot-Admin 缓存配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/2
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class BootAdminCache {
    /**
     * 缓存
     */
    @Getter
    private final StringRedisTemplate redisTemplate;
    /**
     * 前缀
     */
    private final String prefix;


    /**
     * 正规化缓存KEY,按照<code>:</code>进行拼接
     *
     * @param key key
     * @return <code>{@link  #prefix}:key</code>
     */
    public String normaliz(String key) {
        if (StrUtil.isBlank(key)) {
            return null;
        }
        return String.format("%s:%s", prefix, key);
    }

    /**
     * 设置过期时间,单位秒
     *
     * @param key     key
     * @param timeout 过期时间
     * @return 是否成功
     */
    public boolean expire(String key, long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置获取时间
     *
     * @param key     key
     * @param timeout timeout
     * @param unit    时间单位
     * @return boolean
     */
    public boolean expire(String key, long timeout, TimeUnit unit) {
        String redisKey = normaliz(key);
        if (StrUtil.isBlank(redisKey)) {
            return false;
        }
        return this.redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获取过期时间,单位秒
     *
     * @param key key
     * @return 过期时间
     */
    public long getExpire(String key) {
        return getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 获取过期时间
     *
     * @param key  key
     * @param unit 单位秒
     * @return 过期时间
     */
    public long getExpire(String key, TimeUnit unit) {
        String redisKey = normaliz(key);
        if (StrUtil.isBlank(redisKey)) {
            return 0L;
        }

        return this.redisTemplate.getExpire(key, unit);
    }

    /**
     * 设置缓存
     *
     * @param key   key
     * @param value value
     * @return boolean
     */
    public boolean set(String key, String value) {
        String redisKey = normaliz(key);
        if (StrUtil.isBlank(redisKey)) {
            return false;
        }
        this.redisTemplate.opsForValue().set(redisKey, value);
        return true;
    }

    /**
     * 设置有时效的缓存
     *
     * @param key     key
     * @param value   value
     * @param timeout 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return boolean
     */
    public boolean set(String key, String value, long timeout) {
        return set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有时效的缓存
     *
     * @param key      key
     * @param value    value
     * @param timeout  过期时间
     * @param timeUnit 时间单位
     * @return boolean
     */
    public boolean set(String key, String value, long timeout, TimeUnit timeUnit) {
        String redisKey = normaliz(key);
        if (StrUtil.isBlank(redisKey)) {
            return false;
        }
        this.redisTemplate.opsForValue().set(redisKey, value, timeout, timeUnit);
        return true;
    }

    /**
     * 获取缓存
     *
     * @param key key
     * @return value
     */
    @Nullable
    public String get(String key) {
        String redisKey = normaliz(key);
        if (StrUtil.isBlank(redisKey)) {
            return null;
        }
        return this.redisTemplate.opsForValue().get(redisKey);
    }

    /**
     * 删除
     *
     * @param key key
     */
    public void del(String key) {
        String redisKey = normaliz(key);
        if (StrUtil.isBlank(redisKey)) {
            return;
        }
        this.redisTemplate.delete(redisKey);
    }

    /**
     * 批量删除
     *
     * @param key keys
     */
    public void del(String... key) {
        del(Arrays.asList(key));
    }

    /**
     * 批量删除
     *
     * @param keys keys
     */
    public void del(Collection<String> keys) {
        Set<String> redisKeys = keys.stream().map(this::normaliz).collect(Collectors.toSet());
        this.redisTemplate.delete(redisKeys);
    }

}
