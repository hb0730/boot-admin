package com.hb0730.boot.admin.utils.cache;

import com.alicp.jetcache.Cache;
import com.hb0730.boot.admin.commons.constant.RedisConstants;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.utils.spring.SpringUtils;
import com.hb0730.boot.admin.project.system.dict.service.ISystemDictService;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 字典表缓存
 *
 * @author bing_huang
 * @date 2020/06/28 14:50
 * @since V1.0
 */
public class DictCacheUtils {
    /**
     * <p>
     * 获取字典缓存信息
     * </p>
     *
     * @return 字典缓存信息
     * @see ISystemDictService#cache()
     * @see ISystemDictService#getDictForMap()
     */
    public static Map<String, List<Map<String, Object>>> getCache() {
        Cache<String, Map<String, List<Map<String, Object>>>> cache = null;
        try {
            cache = JetCacheUtils.getCache(RedisConstants.REDIS_JETCACHE_NAME_DICT);
        } catch (Exception e) {
            cache();
            cache = JetCacheUtils.getCache(RedisConstants.REDIS_JETCACHE_NAME_DICT);
        }
        return cache.get(getDictKey("-1"));
    }


    /**
     * <p>
     * 删除字典缓存
     * </p>
     */
    public static void deleteCache() {
        Cache cache = JetCacheUtils.getCache(RedisConstants.REDIS_JETCACHE_NAME_DICT);
        cache.remove(getDictKey("-1"));
    }

    /**
     * <p>
     * 更新缓存
     * </p>
     *
     * @see ISystemDictService#cache()
     */
    public static void cache() {
        ISystemDictService service = SpringUtils.getBean(ISystemDictService.class);
        service.cache();
    }

    /**
     * <p>
     * 获取字典项的值
     * </p>
     *
     * @param dictKey     字典编码
     * @param dictDataKey 字典项编码
     * @return 得到字典项值
     */
    public static Object getValue(@NonNull String dictKey, @NonNull String dictDataKey) {
        Map<String, List<Map<String, Object>>> cache = getCache();
        Object object = null;
        if (CollectionUtils.isEmpty(cache)) {
            return null;
        }
        List<Map<String, Object>> maps = cache.get(dictKey);
        if (CollectionUtils.isEmpty(maps)) {
            return null;
        }
        for (Map<String, Object> map : maps) {
            if (object != null) {
                break;
            }
            String label = String.valueOf(map.get(SystemConstants.REDIS_DICT_LABEL));
            if (dictDataKey.equals(label)) {
                object = map.get(SystemConstants.REDIS_DICT_VALUE);
            }
        }
        return object;
    }

    /**
     * 前缀+key
     *
     * @param key key
     * @return 前缀+key
     * @see com.hb0730.boot.admin.commons.constant.SystemConstants.DictConstants#DICT_KEY_PREFIX
     */
    public static String getDictKey(String key) {
        return SystemConstants.DictConstants.DICT_KEY_PREFIX + key;
    }

}
