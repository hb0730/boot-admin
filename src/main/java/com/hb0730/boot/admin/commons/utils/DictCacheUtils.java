package com.hb0730.boot.admin.commons.utils;

import com.hb0730.boot.admin.commons.constant.RedisConstant;
import com.hb0730.boot.admin.project.system.dict.model.vo.DictVO;
import com.hb0730.commons.cache.impl.remote.RedisSpringDataCache;
import com.hb0730.commons.spring.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * dict缓存
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class DictCacheUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictCacheUtils.class);

    /**
     * 获取cacheValue
     *
     * @param key key
     * @return cacheValue
     */
    public static Optional<List<DictVO>> getCacheValue(@NonNull String key) {
        Assert.hasText(key, "key can not be blank");
        @SuppressWarnings("unchecked")
        RedisSpringDataCache<String, List<DictVO>> cache = SpringContextUtils.getBean(RedisSpringDataCache.class);
        try {
            return cache.get(RedisConstant.DICT_KEY_PREFIX + key);
        } catch (Exception e) {
            LOGGER.error("get dict cache error {}", e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * 删除cache
     *
     * @param key cache key
     */
    public static void deleteCache(@NonNull String key) {
        Assert.hasText(key, "key can not be blank");
        @SuppressWarnings("unchecked")
        RedisSpringDataCache<String, List<DictVO>> cache = SpringContextUtils.getBean(RedisSpringDataCache.class);
        cache.delete(RedisConstant.DICT_KEY_PREFIX + key);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("delete cache success, key {}", key);
        }
    }

    /**
     * 设置缓存
     *
     * @param key   key
     * @param dicts data
     */
    public static void setCache(@NonNull String key, @NonNull List<DictVO> dicts) {
        Assert.hasText(key, "key can not be blank");
        Assert.notEmpty(dicts, "dict data not empty");
        @SuppressWarnings("unchecked")
        RedisSpringDataCache<String, List<DictVO>> cache = SpringContextUtils.getBean(RedisSpringDataCache.class);
        cache.put(RedisConstant.DICT_KEY_PREFIX + key, dicts);
    }
}
