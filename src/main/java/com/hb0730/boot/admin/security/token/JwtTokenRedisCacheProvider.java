package com.hb0730.boot.admin.security.token;

import com.hb0730.boot.admin.base.util.JsonUtil;
import com.hb0730.boot.admin.base.util.JwtUtil;
import com.hb0730.boot.admin.config.cache.BootAdminCache;
import com.hb0730.boot.admin.config.cache.CacheUtil;
import com.hb0730.boot.admin.config.cache.KeyValue;
import com.hb0730.boot.admin.security.config.SecurityProperties;
import com.hb0730.boot.admin.security.model.OnlineUser;
import com.hb0730.boot.admin.security.model.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 创建 jwt，并且存储与redis
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/3
 */
@Component
@RequiredArgsConstructor
public class JwtTokenRedisCacheProvider implements TokenProvider, CacheUtil {
    private final SecurityProperties properties;
    private final BootAdminCache cache;

    @Override
    public String createToken(UserInfo userInfo) {
        return createToken(userInfo, null);
    }

    @Override
    public String createToken(UserInfo userInfo, HttpServletRequest request) {
        String _jwtToken = JwtUtil.sign(userInfo.getUsername(), properties.getJwtSecret());
        OnlineUser onlineUser = OnlineUser.convert(userInfo);
        String json = JsonUtil.DEFAULT.toJson(onlineUser);
        String key = getCacheKey(CacheKeyValue.online_user_cache, _jwtToken);
        cache.set(key, json, properties.getTokenValidity());
        return _jwtToken;
    }

    @Override
    public Optional<OnlineUser> getOnlineFromToken(String token) {
        String cacheKey = getCacheKey(CacheKeyValue.online_user_cache, token);
        Optional<String> optional = cache.getStr(cacheKey);
        return optional.flatMap(
            json -> Optional.ofNullable(
                JsonUtil.DEFAULT.fromJson(json, OnlineUser.class)
            )
        );
    }

    @Override
    public boolean checkRenewal(String token) {
        if (!properties.isDelayToken()) {
            return false;
        }
        // 获取剩余时间 单位/秒
        String key = getCacheKey(CacheKeyValue.online_user_cache, token);
        long expire = cache.getExpire(key);
        // 是否小于30
        if (expire < properties.getTokenDetect() && expire != 0) {
            // 重新设置过期时间
            cache.expire(key, properties.getTokenValidity());
        }
        return true;
    }

    @Override
    public boolean removeToken(String token) {
        String cacheKey = getCacheKey(CacheKeyValue.online_user_cache, token);
        return cache.del(cacheKey);
    }

    @Getter
    public enum CacheKeyValue implements KeyValue {
        online_user_cache(
            "token",
            "在线用户"
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
