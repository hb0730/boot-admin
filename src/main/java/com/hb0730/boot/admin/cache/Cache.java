package com.hb0730.boot.admin.cache;


import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 缓存接口
 *
 * @param <K> key类型
 * @param <V> value类型
 * @author bing_huang
 * @date 2020/07/18 13:03
 * @since V2.0
 */
public interface Cache<K, V> {
    /**
     * 根据key获取
     *
     * @param key 缓存key 不能为空
     * @return cache 缓存值
     */
    @Nonnull
    Optional<V> get(@Nonnull K key);

    /**
     * 缓存过期时效的值
     *
     * @param key      缓存key
     * @param value    缓存值
     * @param timeout  过期时长,不能小于1
     * @param timeUnit 超时类型
     */
    void put(@Nonnull K key, @Nonnull V value, long timeout, @Nonnull TimeUnit timeUnit);

    /**
     * 设置缓存 , key不存在则设置时效缓存，否则不操作
     *
     * @param key      缓存key,不为null
     * @param value    缓存value,不为null
     * @param timeout  过期时长,不能小于1
     * @param timeUnit 时间类型,不为null
     * @return true:缓存key不存在并已重新设置;false:缓存前key存在;null:其他原因
     */
    Boolean putIfAbsent(@Nonnull K key, @Nonnull V value, long timeout, @Nonnull TimeUnit timeUnit);

    /**
     * 永久缓存
     *
     * @param key   缓存key不为null
     * @param value 缓存value,不为空
     */
    void put(@Nonnull K key, @Nonnull V value);

    /**
     * 根据key删除
     *
     * @param key 缓存key 不为null
     */
    void delete(@Nonnull K key);
}
