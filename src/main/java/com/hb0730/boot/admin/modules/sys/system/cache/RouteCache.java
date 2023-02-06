package com.hb0730.boot.admin.modules.sys.system.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hb0730.boot.admin.base.util.JsonUtil;
import com.hb0730.boot.admin.config.cache.BootAdminCache;
import com.hb0730.boot.admin.config.cache.CacheUtil;
import com.hb0730.boot.admin.config.cache.KeyValue;
import com.hb0730.boot.admin.modules.sys.system.model.vo.VueMenuRouteVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 缓存当前用户路由信息
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/6
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class RouteCache implements CacheUtil {
    private final BootAdminCache cache;

    /**
     * 缓存用户路由
     *
     * @param userid 用户路由
     * @param routes 路由
     */
    public void putCache(String userid, List<VueMenuRouteVO> routes) {
        log.info("【用户路由】缓存用户: {} 路由信息, 缓存时间: 2小时", userid);
        String cacheKey = getCacheKey(RouteCacheKey.default_key, userid);
        String json = JsonUtil.DEFAULT.toJson(routes);
        cache.set(cacheKey, json, 60 * 60 * 2);
    }

    /**
     * 获取用户路由
     *
     * @param userid 用户ID
     * @return 用户路由
     */
    public Optional<List<VueMenuRouteVO>> getCache(String userid) {
        log.info("【用户路由】获取用户: {} 缓存的路由信息", userid);
        String cacheKey = getCacheKey(RouteCacheKey.default_key, userid);
        Optional<String> jsonOptional = cache.getStr(cacheKey);
        return jsonOptional.flatMap(e ->
            Optional.ofNullable(JsonUtil.DEFAULT.fromJson(e, new TypeReference<List<VueMenuRouteVO>>() {
            }))
        );
    }

    /**
     * 清理用户路由
     *
     * @param userid .
     */
    public void removeCache(String userid) {
        log.info("【用户路由】清理用户: {} 缓存的路由信息", userid);
        String cacheKey = getCacheKey(RouteCacheKey.default_key, userid);
        cache.del(cacheKey);
    }


    public enum RouteCacheKey implements KeyValue {
        default_key("route", "当前用户路由");
        private final String prefix;
        private final String desc;

        RouteCacheKey(String prefix, String desc) {
            this.prefix = prefix;
            this.desc = desc;
        }

        @Override
        public String getPrefix() {
            return this.prefix;
        }

        @Override
        public String getDesc() {
            return this.desc;
        }
    }
}
