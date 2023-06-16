package com.hb0730.boot.admin.security.token;

import cn.hutool.core.collection.CollectionUtil;
import com.hb0730.boot.admin.base.util.JsonUtil;
import com.hb0730.boot.admin.config.cache.BootAdminCache;
import com.hb0730.boot.admin.config.cache.CacheUtil;
import com.hb0730.boot.admin.config.cache.KeyValue;
import com.hb0730.boot.admin.security.config.SecurityProperties;
import com.hb0730.boot.admin.security.model.UserInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/4
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class RedisCacheUserProvider implements UserCacheProvider, CacheUtil {
    private final BootAdminCache cache;
    private final SecurityProperties properties;

    @Override
    public boolean cacheUser(String key, UserInfo userInfo) {
        String json = JsonUtil.DEFAULT.toJson(userInfo);
        String cacheKey = getCacheKey(CacheKeyValue.login_user_cache, key);
        log.info("【缓存登录用户】 获取登录用户信息: key {} value {}", cacheKey, json);
        return cache.set(cacheKey, json, properties.getTokenValidity());
    }

    @Override
    public boolean clearUser(String key) {
        String cacheKey = getCacheKey(CacheKeyValue.login_user_cache, key);
        log.info("【缓存登录用户】清理登录用户信息: key {}", cacheKey);
        return cache.del(cacheKey);
    }

    @Override
    public Optional<UserInfo> getUser(String key) {
        String cacheKey = getCacheKey(CacheKeyValue.login_user_cache, key);
        log.info("【缓存登录用户】 获取登录用户信息: {}", cacheKey);
        return cache.getStr(cacheKey).flatMap(json ->
                Optional.ofNullable(JsonUtil.DEFAULT.fromJson(json, UserInfo.class))
        );
    }

    @Override
    public Optional<List<UserInfo>> getCacheUsers() {
        // 获取所有的用户缓存KEYS
        String cacheKey = getCacheKey(CacheKeyValue.login_user_cache, "*");
        Set<String> keys = cache.getKeys(cacheKey);
        if (CollectionUtil.isEmpty(keys)) {
            log.info("【缓存登录用户】 未获取到登录用户信息");
            return Optional.empty();
        }
        List<UserInfo> userInfoList=new ArrayList<>();
        for (String key : keys) {
            Optional<String> cacheValue = cache.defaultGet(key);
            if (cacheValue.isPresent()) {
                UserInfo cacheUserInfo = JsonUtil.DEFAULT.fromJson(cacheValue.get(), UserInfo.class);
                userInfoList.add(cacheUserInfo);
            }
        }
        return Optional.of(userInfoList);
    }

    @Getter
    public enum CacheKeyValue implements KeyValue {
        login_user_cache(
                "login-user",
                "登录用户"
        ),
        ;
        private final String prefix;
        private final String name;

        CacheKeyValue(String prefix, String name) {
            this.prefix = prefix;
            this.name = name;
        }
    }
}
