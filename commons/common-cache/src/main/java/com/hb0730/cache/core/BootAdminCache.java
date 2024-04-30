package com.hb0730.cache.core;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 缓存
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
@Setter
public class BootAdminCache implements ICache {
    private String prefix = "boot_admin";

    private ICache cache;


    /**
     * 正规化缓存KEY
     *
     * @param key key
     * @return prefix:key
     */
    protected String normalizeKey(String key) {
        if (StrUtil.isBlank(prefix)) {
            return key;
        }
        return prefix + ":" + key;
    }

    @Override
    public Set<String> scanKeys(String pattern) {
        if (StrUtil.isBlank(pattern)) {
            pattern = "*";
        }
        if (!pattern.endsWith("*")) {
            pattern += "*";
        }

        return cache.scanKeys(pattern);
    }

    @Override
    public void delete(String key) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return;
        }
        cache.delete(_key);
    }

    @Override
    public void delete(Set<String> keys) {
        if (CollectionUtil.isEmpty(keys)) {
            return;
        }
        Set<String> _keys = Sets.newHashSet();
        keys.forEach(key -> _keys.add(normalizeKey(key)));
        cache.delete(_keys);
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
    public boolean expire(String key, long timeout) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.expire(_key, timeout);
    }

    @Override
    public Object getObject(String key) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return null;
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
    public boolean setObjectIfAbsent(String key, Object value) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.setObjectIfAbsent(_key, value);
    }

    @Override
    public String getString(String key) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return null;
        }
        return cache.getString(_key);
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
    public boolean setStringIfAbsent(String key, String value) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return false;
        }
        return cache.setStringIfAbsent(_key, value);
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
    public String hGet(String key, String item) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return null;
        }
        return cache.hGet(_key, item);
    }

    @Override
    public List<String> hmget(String key, List<String> items) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return null;
        }
        return cache.hmget(_key, items);
    }

    @Override
    public Map<String, String> hValues(String key) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return null;
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
    public Set<String> sGet(String key) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return null;
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
    public List<String> lGet(String key, long start, long end) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return null;
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
    public String lGetIndex(String key, long index) {
        String _key = normalizeKey(key);
        if (StrUtil.isBlank(_key)) {
            return null;
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
}