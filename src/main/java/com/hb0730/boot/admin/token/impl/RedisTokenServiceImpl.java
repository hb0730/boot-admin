package com.hb0730.boot.admin.token.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.cache.BootAdminCache;
import com.hb0730.boot.admin.security.model.User;
import com.hb0730.boot.admin.token.AbstractTokenService;
import com.hb0730.boot.admin.token.cache.TokenCache;
import com.hb0730.boot.admin.token.configuration.TokenProperties;
import com.hb0730.commons.lang.date.DateUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis 实现
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class RedisTokenServiceImpl extends AbstractTokenService {
    private final TokenCache cache;

    public RedisTokenServiceImpl(TokenProperties properties, BootAdminCache globalCache) {
        super(properties);
        cache = new TokenCache(globalCache);
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        String accessToken = getAccessToken(request);
        if (StrUtil.isNotBlank(accessToken)) {
            String userToken = cache.getUserToken(accessToken);
            if (StrUtil.isNotBlank(userToken)) {
                return cache.getUserInfo(userToken);
            }
        }
        return null;
    }

    @Override
    public String createAccessToken(User user) {
        String token = IdUtil.fastUUID();
        user.setToken(token);
        setUserAgent(user);
        //缓存
        refreshAccessToken(user);
        String accessToken = extractKey(token);
        // 缓存token
        cache.setUserToken(accessToken, token, super.getProperties().getExpireTime(), super.getProperties().getTimeUnit());
        return accessToken;
    }

    @Override
    public void refreshAccessToken(User user) {
        long expire = TimeUnit.MILLISECONDS.convert(super.getProperties().getExpireTime(), super.getProperties().getTimeUnit());
        // 设置登录时长与过期时间
        user.setLoginTime(DateUtils.now());
        Date expireTim = DateUtils.add(user.getLoginTime(), expire, TimeUnit.MILLISECONDS);
        user.setExpireTime(expireTim);
        //缓存用户
        cache.setUserInfo(user.getToken(), user, expire, TimeUnit.MILLISECONDS);
    }

    @Override
    public void verifyAccessToken(HttpServletRequest request) {
        User loginUser = getLoginUser(request);
        if (Objects.nonNull(loginUser)) {
            Date expireTime = loginUser.getExpireTime();
            Date currentTime = DateUtils.now();
            long refreshTime = TimeUnit.MILLISECONDS.convert(super.getProperties().getRefreshTime(), super.getProperties().getTimeUnit());
            // 校验 刷新
            if (expireTime.getTime() - currentTime.getTime() <= refreshTime) {
                String accessToken = getAccessToken(request);
                cache.setUserToken(accessToken, loginUser.getToken(), super.getProperties().getExpireTime(),
                    super.getProperties().getTimeUnit());
                refreshAccessToken(loginUser);
            }
        }
    }

    @Override
    public void delLoginUser(HttpServletRequest request) {
        String accessToken = getAccessToken(request);
        if (StrUtil.isNotBlank(accessToken)) {
            deleteAccessToken(accessToken);
        }
    }

    @Override
    public void deleteAccessToken(String accessToken) {
        if (StrUtil.isNotBlank(accessToken)) {
            String userToken = cache.getUserToken(accessToken);
            cache.delUserInfo(userToken);
            cache.delUserToken(accessToken);
        }
    }

    @Override
    public Map<String, UserDetails> getOnline() {
        Map<String, UserDetails> maps = Maps.newHashMap();
        try {
            String all = cache.getUserTokenKey("*");
            BootAdminCache bootAdminCache = cache.getCache();
            Set<String> keys = bootAdminCache.getRedisTemplate().keys(bootAdminCache.normaliz(all));
            if (CollUtil.isEmpty(keys)) {
                return maps;
            }
            for (String key : keys) {
                String accessToken = StrUtil.removePrefix(key, bootAdminCache.normaliz(cache.getUserTokenKey("")));
                String token = bootAdminCache.getRedisTemplate().opsForValue().get(key);
                User userInfo = cache.getUserInfo(token);
                maps.put(accessToken, userInfo);
            }
        } catch (
            Exception e) {
            e.printStackTrace();
        }
        return maps;
    }
}
