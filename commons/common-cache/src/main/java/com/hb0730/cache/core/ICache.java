package com.hb0730.cache.core;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 缓存接口
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
public interface ICache {

    /**
     * 查找所有的key
     *
     * @param pattern 匹配模式,如: *
     * @return keys
     */
    Set<String> scanKeys(String pattern);

    /**
     * 删除key
     *
     * @param key key
     */
    void delete(String key);

    /**
     * 删除多个key
     *
     * @param keys keys
     */
    void delete(Set<String> keys);

    /**
     * 删除多个key
     *
     * @param keys keys
     */
    default void delete(String... keys) {
        delete(Set.of(keys));
    }

    /**
     * key 是否存在
     *
     * @param key key
     * @return true/false
     */
    boolean hasKey(String key);

    /**
     * 获取key的过期时间
     *
     * @param key key
     * @return 过期时间，单位秒,返回0代表永久有效,返回-1代表key不存在
     */
    long getExpire(String key);

    /**
     * 设置key的过期时间
     *
     * @param key     key
     * @param timeout 过期时间,单位秒
     * @return true/false
     */
    boolean expire(String key, long timeout);

    /**
     * 设置key的过期时间
     *
     * @param key     key
     * @param timeout 过期时间
     * @param unit    时间单位
     * @return true/false
     */
    default boolean expire(String key, long timeout, TimeUnit unit) {
        return expire(key, unit.toSeconds(timeout));
    }

    /*==========================Object==============================*/

    /**
     * 获取key的值
     *
     * @param key key
     * @return value
     */
    Object getObject(String key);

    /**
     * 设置key的值
     *
     * @param key   key
     * @param value value
     */
    boolean setObject(String key, Object value);

    /**
     * 设置key的值
     *
     * @param key     key
     * @param value   value
     * @param timeout 过期时间
     * @param unit    时间单位
     */
    default boolean setObject(String key, Object value, long timeout, TimeUnit unit) {
        boolean result = setObject(key, value);
        if (result) {
            expire(key, timeout, unit);
        }
        return result;
    }

    /**
     * 设置key的值
     *
     * @param key     key
     * @param value   value
     * @param timeout 过期时间 单位秒
     */
    default boolean setObject(String key, Object value, long timeout) {
        return setObject(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置key的值,如果key存在则不设置
     *
     * @param key   key
     * @param value value
     * @return true/false
     */
    boolean setObjectIfAbsent(String key, Object value);

    /**
     * 设置key的值,如果key存在则不设置
     *
     * @param key     key
     * @param value   value
     * @param timeout 过期时间
     * @param unit    时间单位
     * @return true/false
     */
    default boolean setObjectIfAbsent(String key, Object value, long timeout, TimeUnit unit) {
        boolean result = setObjectIfAbsent(key, value);
        if (result) {
            expire(key, timeout, unit);
        }
        return result;
    }

    /**
     * 设置key的值,如果key存在则不设置
     *
     * @param key     key
     * @param value   value
     * @param timeout 过期时间 单位秒
     * @return true/false
     */
    default boolean setObjectIfAbsent(String key, Object value, long timeout) {
        return setObjectIfAbsent(key, value, timeout, TimeUnit.SECONDS);
    }

    /*==========================String==============================*/

    /**
     * 获取key的值
     *
     * @param key key
     * @return value
     */
    String getString(String key);

    /**
     * 设置key的值
     *
     * @param key   key
     * @param value value
     */
    boolean setString(String key, String value);

    /**
     * 设置key的值
     *
     * @param key     key
     * @param value   value
     * @param timeout 过期时间
     * @param unit    时间单位
     */
    default boolean setString(String key, String value, long timeout, TimeUnit unit) {
        boolean result = setString(key, value);
        if (result) {
            expire(key, timeout, unit);
        }
        return result;
    }

    /**
     * 设置key的值
     *
     * @param key     key
     * @param value   value
     * @param timeout 过期时间 单位秒
     */
    default boolean setString(String key, String value, long timeout) {
        return setString(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置key的值,如果key存在则不设置
     *
     * @param key   key
     * @param value value
     * @return true/false
     */
    boolean setStringIfAbsent(String key, String value);

    /**
     * 设置key的值,如果key存在则不设置
     *
     * @param key     key
     * @param value   value
     * @param timeout 过期时间
     * @param unit    时间单位
     * @return true/false
     */
    default boolean setStringIfAbsent(String key, String value, long timeout, TimeUnit unit) {
        boolean result = setStringIfAbsent(key, value);
        if (result) {
            expire(key, timeout, unit);
        }
        return result;
    }

    /**
     * 设置key的值,如果key存在则不设置
     *
     * @param key     key
     * @param value   value
     * @param timeout 过期时间 单位秒
     * @return true/false
     */
    default boolean setStringIfAbsent(String key, String value, long timeout) {
        return setStringIfAbsent(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return 加上指定增量之后的值
     */
    long incr(String key, long delta);

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return 减去指定减量之后的值
     */
    long decr(String key, long delta);

    /*==========================Map==============================*/

    /**
     * 获取hashKey对应的键值
     *
     * @param key  键
     * @param item 项
     * @return 值
     */
    String hGet(String key, String item);

    /**
     * 获取hashKey对应的键值
     *
     * @param key   键
     * @param items 项
     * @return 值
     */
    List<String> hmget(String key, List<String> items);

    /**
     * 获取hashKey对应的键值
     *
     * @param key   键
     * @param items 项
     * @return 值
     */
    default List<String> hmget(String key, String... items) {
        return hmget(key, List.of(items));
    }

    /**
     * 设置hashKey对应的所有键值
     *
     * @param key 键
     * @return true/false
     */
    Map<String, String> hValues(String key);

    /**
     * 设置hashKey对应的所有键值
     *
     * @param key 键
     * @return true/false
     */
    boolean hmset(String key, Map<String, String> map);

    /**
     * 设置hashKey对应的所有键值
     *
     * @param key     键
     * @param map     map
     * @param timeout 过期时间
     * @param unit    时间单位
     * @return true/false
     */
    default boolean hmset(String key, Map<String, String> map, long timeout, TimeUnit unit) {
        boolean result = hmset(key, map);
        if (result) {
            expire(key, timeout, unit);
        }
        return result;
    }

    /**
     * 设置hashKey对应的所有键值
     *
     * @param key     键
     * @param map     map
     * @param timeout 过期时间 单位秒
     * @return true/false
     */
    default boolean hmset(String key, Map<String, String> map, long timeout) {
        return hmset(key, map, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置hashKey对应的键值
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true/false
     */
    boolean hset(String key, String item, String value);

    /**
     * 设置hashKey对应的键值
     *
     * @param key     键
     * @param item    项
     * @param value   值
     * @param timeout 过期时间
     * @param unit    时间单位
     * @return true/false
     */
    default boolean hset(String key, String item, String value, long timeout, TimeUnit unit) {
        boolean result = hset(key, item, value);
        if (result) {
            expire(key, timeout, unit);
        }
        return result;
    }

    /**
     * 设置hashKey对应的键值
     *
     * @param key     键
     * @param item    项
     * @param value   值
     * @param timeout 过期时间 单位秒
     * @return true/false
     */
    default boolean hset(String key, String item, String value, long timeout) {
        return hset(key, item, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 删除hashKey对应的键值
     *
     * @param key  键
     * @param item 项
     * @return true/false
     */
    boolean hdel(String key, String... item);

    /**
     * 判断hashKey是否存在
     *
     * @param key  键
     * @param item 项
     * @return .
     */
    boolean hHasKey(String key, String item);

    /**
     * 递增
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return 加上指定增量之后的值
     */
    double hincr(String key, String item, double by);

    /**
     * 递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return 减去指定减量之后的值
     */
    double hdecr(String key, String item, double by);

    /*==========================set==============================*/

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return .
     */
    Set<String> sGet(String key);

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return .
     */
    boolean sHasKey(String key, String value);

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值
     * @return .
     */
    long sSet(String key, String... values);

    /**
     * 将set数据放入缓存
     *
     * @param key     键
     * @param timeout 过期时间
     * @param unit    时间单位
     * @param values  值
     * @return .
     */
    default long sSet(String key, long timeout, TimeUnit unit, String... values) {
        long count = sSet(key, values);
        if (count > 0) {
            expire(key, timeout, unit);
        }
        return count;
    }

    /**
     * 将set数据放入缓存
     *
     * @param key     键
     * @param timeout 过期时间 单位秒
     * @param values  值
     * @return .
     */
    default long sSet(String key, long timeout, String... values) {
        return sSet(key, timeout, TimeUnit.SECONDS, values);
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return .
     */
    long sSize(String key);

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值
     * @return .
     */
    long sRemove(String key, String... values);

    /*==========================list==============================*/

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return .
     */
    List<String> lGet(String key, long start, long end);

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return .
     */
    long lSize(String key);

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return .
     */
    String lGetIndex(String key, long index);

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return .
     */
    boolean lSet(String key, String value);

    /**
     * 将list放入缓存
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间
     * @param unit    时间单位
     * @return .
     */
    default boolean lSet(String key, String value, long timeout, TimeUnit unit) {
        boolean result = lSet(key, value);
        if (result) {
            expire(key, timeout, unit);
        }
        return result;
    }

    /**
     * 将list放入缓存
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间 单位秒
     * @return .
     */
    default boolean lSet(String key, String value, long timeout) {
        return lSet(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 将list放入缓存
     *
     * @param key    键
     * @param values 值
     * @return .
     */
    boolean lSet(String key, List<String> values);

    /**
     * 将list放入缓存
     *
     * @param key     键
     * @param values  值
     * @param timeout 过期时间
     * @param unit    时间单位
     * @return .
     */
    default boolean lSet(String key, List<String> values, long timeout, TimeUnit unit) {
        boolean result = lSet(key, values);
        if (result) {
            expire(key, timeout, unit);
        }
        return result;
    }

    /**
     * 将list放入缓存
     *
     * @param key     键
     * @param values  值
     * @param timeout 过期时间 单位秒
     * @return .
     */
    default boolean lSet(String key, List<String> values, long timeout) {
        return lSet(key, values, timeout, TimeUnit.SECONDS);
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return .
     */
    boolean lUpdateIndex(String key, long index, String value);

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return .
     */
    long lRemove(String key, long count, String value);

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return .
     */
    default long lRemove(String key, long count, String... value) {
        long result = 0;
        for (String s : value) {
            result += lRemove(key, count, s);
        }
        return result;
    }
}