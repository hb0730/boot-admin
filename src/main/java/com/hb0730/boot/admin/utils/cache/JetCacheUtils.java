package com.hb0730.boot.admin.utils.cache;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.method.SpringCacheContext;
import com.alicp.jetcache.anno.support.CacheManager;
import com.hb0730.boot.admin.commons.constant.RedisConstants;
import org.springframework.lang.NonNull;

/**
 * jetcache utils
 *
 * @author bing_huang
 * @date 2020/06/28 14:49
 * @since V1.0
 */
public class JetCacheUtils {
    /**
     * 获取当前cacheManger
     *
     * @return {@link CacheManager}
     * @see CacheManager#defaultManager()
     */
    public static CacheManager getCacheManger() {
        return CacheManager.defaultManager();
    }

    /**
     * 获取cache<br>
     * 在获取cache时,请先初始化不然会报{@link NullPointerException}
     *
     * @param are       如果需要连接多个缓存系统，可在配置多个cache area，这个属性指定要使用的那个area的name
     * @param cacheName 指定缓存的名称，不是必须的，如果没有指定，会使用类名+方法名。
     * @return 获取cache
     * @see SpringCacheContext#init()
     */
    public static Cache getCache(@NonNull String are, @NonNull String cacheName) {
        return getCacheManger().getCache(are, cacheName);
    }

    /**
     * 获取cache <br>
     * are 默认为{@link RedisConstants#REDIS_JETCACHE_AREA}
     *
     * @param cacheName 指定缓存的名称，不是必须的，如果没有指定，会使用类名+方法名。
     * @return 获取cache
     * @see JetCacheUtils#getCache(String, String)
     */
    public static Cache getCache(@NonNull String cacheName) {
        return getCache(RedisConstants.REDIS_JETCACHE_AREA, cacheName);
    }

}
