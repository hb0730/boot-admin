package com.hb0730.boot.admin.token.cache;

import cn.hutool.core.util.StrUtil;
import com.hb0730.boot.admin.cache.BootAdminCache;
import com.hb0730.boot.admin.cache.CacheUtil;
import com.hb0730.boot.admin.cache.KeyValue;
import com.hb0730.boot.admin.commons.utils.JsonUtils;
import com.hb0730.boot.admin.security.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/2
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class TokenCache implements CacheUtil {
    @Getter
    private final BootAdminCache cache;
    private static TokenKeyValue keyValue = new TokenKeyValue();
    private static TokenUserValue userValue = new TokenUserValue();

    public void setUserToken(String accessToken, String userToken, long timeout, TimeUnit timeUnit) {
        String cacheKey = getCacheKey(keyValue, accessToken);
        cache.set(cacheKey, userToken, timeout, timeUnit);
    }

    public String getUserTokenKey(String accessToken) {
        return getCacheKey(keyValue, accessToken);
    }

    public String getUserToken(String accessToken) {
        String cacheKey = getCacheKey(keyValue, accessToken);
        return cache.get(cacheKey);
    }

    public void delUserToken(String accessToken) {
        String cacheKey = getCacheKey(keyValue, accessToken);
        cache.del(cacheKey);
    }

    public void setUserInfo(String token, User user, long timeout, TimeUnit timeUnit) {
        String cacheKey = getCacheKey(userValue, token);
        cache.set(cacheKey, JsonUtils.objectToJson(user), timeout, timeUnit);
    }

    public User getUserInfo(String token) {
        String cacheKey = getCacheKey(userValue, token);
        String json = cache.get(cacheKey);
        if (StrUtil.isNotBlank(json)) {
            return JsonUtils.jsonToObject(json, User.class);
        }
        return null;
    }

    public void delUserInfo(String token) {
        String cacheKey = getCacheKey(userValue, token);
        cache.del(cacheKey);
    }

    private static class TokenKeyValue implements KeyValue {
        @Override
        public String getPrefix() {
            return "access_token";
        }

        @Override
        public String getName() {
            return "缓存用户token";
        }
    }

    private static class TokenUserValue implements KeyValue {
        @Override
        public String getPrefix() {
            return "user_token";
        }

        @Override
        public Class<?> getClazz() {
            return User.class;
        }

        @Override
        public String getName() {
            return "缓存用户信息";
        }
    }
}
