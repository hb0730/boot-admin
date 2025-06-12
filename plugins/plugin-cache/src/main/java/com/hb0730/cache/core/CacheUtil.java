package com.hb0730.cache.core;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Sets;
import com.hb0730.base.AppUtil;
import com.hb0730.base.utils.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 缓存工具类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/23
 */
@RequiredArgsConstructor
public class CacheUtil implements ICache {
    private final ICache cache;
    private final String prefix;


    /**
     * 正规化缓存KEY
     *
     * @param key key
     * @return prefix:key
     */
    public String normalizeKey(String key) {
        if (StrUtil.isBlank(prefix)) {
            return key;
        }
        return prefix + ":" + key;
    }

    @Override
    public Optional<Set<String>> scanKeys(String pattern) {
        if (StrUtil.isBlank(pattern)) {
            pattern = "*";
        }
        if (!pattern.endsWith("*")) {
            pattern += "*";
        }

        return cache.scanKeys(pattern);
    }

    @Override
    public void del(String key) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return;
        }
        cache.del(_key);
    }

    @Override
    public void del(Set<String> keys) {
        if (CollectionUtil.isEmpty(keys)) {
            return;
        }
        Set<String> _keys = Sets.newHashSet();
        keys.forEach(key -> _keys.add(normalizeKey(key)));
        cache.del(_keys);
    }

    @Override
    public void delScan(String pattern) {
        String _pattern = normalizeKey(pattern);
        if (StrUtil.isBlank(_pattern)) {
            return;
        }
        if (!_pattern.endsWith("*")) {
            _pattern += "*";
        }
        cache.delScan(_pattern);
    }

    @Override
    public boolean hasKey(String key) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.hasKey(_key);
    }

    @Override
    public long getExpire(String key) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return -1L;
        }

        return cache.getExpire(_key);
    }

    @Override
    public boolean expire(String key, long expire) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.expire(_key, expire);
    }

    @Override
    public Optional<Object> getObject(String key) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return Optional.empty();
        }
        return cache.getObject(_key);
    }

    @Override
    public boolean setObject(String key, Object value) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.setObject(_key, value);
    }

    @Override
    public boolean setObject(String key, Object value, long timeout, TimeUnit unit) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.setObject(_key, value, timeout, unit);
    }

    @Override
    public boolean setObjectIfAbsent(String key, Object value) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.setObjectIfAbsent(_key, value);
    }

    @Override
    public boolean setObjectIfAbsent(String key, Object value, long timeout, TimeUnit unit) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.setObjectIfAbsent(_key, value, timeout, unit);
    }


    @Override
    public boolean setJson(String key, Object value) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.setJson(_key, value);
    }

    @Override
    public boolean setJson(String key, Object value, long timeout, TimeUnit unit) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.setJson(_key, value, timeout, unit);
    }

    @Override
    public Optional<String> getString(String key) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return Optional.empty();
        }
        return cache.getString(_key);
    }

    @Override
    public Optional<List<String>> getString(Collection<String> keys) {
        if (CollectionUtil.isEmpty(keys)) {
            return Optional.empty();
        }
        Set<String> _keys = Sets.newHashSet();
        keys.forEach(key -> _keys.add(normalizeKey(key)));
        return cache.getString(_keys);
    }

    @Override
    public Optional<List<String>> getString(Collection<String> keys, boolean normalize) {
        if (CollectionUtil.isEmpty(keys)) {
            return Optional.empty();
        }
        if (!normalize) {
            return cache.getString(keys);
        }
        Set<String> _keys = Sets.newHashSet();
        keys.forEach(key -> _keys.add(normalizeKey(key)));
        return cache.getString(CollectionUtil.newArrayList(_keys));
    }

    @Override
    public boolean setString(String key, String value) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.setString(_key, value);
    }

    @Override
    public boolean setString(String key, String value, long timeout, TimeUnit unit) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.setString(_key, value, timeout, unit);
    }

    @Override
    public boolean setStringIfAbsent(String key, String value) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.setStringIfAbsent(_key, value);
    }

    @Override
    public boolean setStringIfAbsent(String key, String value, long timeout, TimeUnit unit) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.setStringIfAbsent(_key, value, timeout, unit);
    }

    @Override
    public long incr(String key, long delta) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return 0;
        }
        return cache.incr(_key, delta);
    }

    @Override
    public long decr(String key, long delta) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return 0;
        }
        return cache.decr(_key, delta);
    }

    @Override
    public Optional<String> hGet(String key, String item) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return Optional.empty();
        }
        return cache.hGet(_key, item);
    }

    @Override
    public Optional<List<String>> hmget(String key, List<String> items) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return Optional.empty();
        }
        return cache.hmget(_key, items);
    }

    @Override
    public Optional<Map<String, String>> hValues(String key) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return Optional.empty();
        }
        return cache.hValues(_key);
    }

    @Override
    public boolean hmset(String key, Map<String, String> map) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.hmset(_key, map);
    }

    @Override
    public boolean hset(String key, String item, String value) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.hset(_key, item, value);
    }

    @Override
    public boolean hdel(String key, String... item) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.hdel(_key, item);
    }

    @Override
    public boolean hHasKey(String key, String item) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.hHasKey(_key, item);
    }

    @Override
    public double hincr(String key, String item, double by) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return 0;
        }
        return cache.hincr(_key, item, by);
    }

    @Override
    public double hdecr(String key, String item, double by) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return 0;
        }
        return cache.hdecr(_key, item, by);
    }

    @Override
    public Optional<Set<String>> sGet(String key) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return Optional.empty();
        }
        return cache.sGet(_key);
    }

    @Override
    public boolean sHasKey(String key, String value) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.sHasKey(_key, value);
    }

    @Override
    public long sSet(String key, String... values) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return 0;
        }
        return cache.sSet(_key, values);
    }

    @Override
    public long sSize(String key) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return 0;
        }
        return cache.sSize(_key);
    }

    @Override
    public long sRemove(String key, String... values) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return 0;
        }
        return cache.sRemove(_key, values);
    }

    @Override
    public List<String> randomMembers(String key, int size) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return CollectionUtil.newArrayList();
        }
        return cache.randomMembers(_key, size);
    }

    @Override
    public Optional<List<String>> lGet(String key, long start, long end) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return Optional.empty();
        }
        return cache.lGet(_key, start, end);
    }

    @Override
    public long lSize(String key) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return 0;
        }
        return cache.lSize(_key);
    }

    @Override
    public Optional<String> lGetIndex(String key, long index) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return Optional.empty();
        }
        return cache.lGetIndex(_key, index);
    }

    @Override
    public boolean lSet(String key, String value) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.lSet(_key, value);
    }

    @Override
    public boolean lSet(String key, List<String> values) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.lSet(_key, values);
    }

    @Override
    public boolean lUpdateIndex(String key, long index, String value) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.lUpdateIndex(_key, index, value);
    }

    @Override
    public long lRemove(String key, long count, String value) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return 0;
        }
        return cache.lRemove(_key, count, value);
    }


    /**
     * 从缓存中获取数据,如果不存在则执行回调,并将结果存入缓存
     *
     * @param params   参数
     * @param callBack 回调
     * @param <V>      返回值
     * @param <P>      参数
     * @return 数据
     */
    public static <V, P> V getFromCache(P params, CallBack<V, P> callBack) {
        if (callBack.isSkipCache(params)) {
            return callBack.doCallback(params);
        }
        String key = callBack.getCachedKey(params);
        if (StrUtil.isBlank(key)) {
            return null;
        }
        Cache cache = AppUtil.getBean(CacheManager.class).getCache(callBack.getCacheName());
        if (cache != null) {
            return cache.get(key, () -> callBack.doCallback(params));
        }
        return null;
    }

    public abstract static class CallBack<V, P> {
        protected String cacheName;

        public CallBack(String cacheName) {
            this.cacheName = cacheName;
        }

        public String getCacheName() {
            return cacheName;
//            return String.format("%s:%s", AppUtil.getProperty("zoom.cache.prefix", "zoom"), cacheName);
        }

        /**
         * 是否跳过缓存
         *
         * @param params 参数
         * @return 是否跳过
         */
        protected boolean isSkipCache(P params) {
            return false;
        }

        /**
         * 获取缓存key
         *
         * @param params 参数
         * @return key
         */
        public abstract String getCachedKey(P params);

        /**
         * 获取缓存对象
         *
         * @param params 参数
         * @return 缓存对象
         */
        public abstract V doCallback(P params);
    }
}
