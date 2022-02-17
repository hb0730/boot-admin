package com.hb0730.boot.admin.token.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.security.model.User;
import com.hb0730.boot.admin.token.AbstractTokenService;
import com.hb0730.boot.admin.token.configuration.TokenProperties;
import com.hb0730.commons.lang.date.DateUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

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
    private final StringRedisTemplate stringRedisTemplate;
    private final RedisTemplate<String, User> redisTemplate;

    public RedisTokenServiceImpl(TokenProperties properties, StringRedisTemplate stringRedisTemplate,
                                 RedisTemplate<String, User> redisTemplate) {
        super(properties);
        this.stringRedisTemplate = stringRedisTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        String accessToken = getAccessToken(request);
        if (StrUtil.isNotBlank(accessToken)) {
            String accessTokenKey = getAccessTokenKey(accessToken);
            String userToken = stringRedisTemplate.opsForValue().get(accessTokenKey);
            if (StrUtil.isNotBlank(userToken)) {
                return redisTemplate.opsForValue().get(getUserTokenKey(userToken));
            }
        }
        return null;
    }

    @Override
    public String createAccessToken(User user) {
        String token = IdUtil.fastUUID();
        user.setToken(token);
        setUserAgent(user);
        refreshAccessToken(user);
        String accessToken = extractKey(token);
        String accessTokenKey = getAccessTokenKey(accessToken);
        stringRedisTemplate.opsForValue().set(accessTokenKey, token, super.getProperties().getExpireTime(),
            super.getProperties().getTimeUnit());
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
        String userTokenKey = getUserTokenKey(user.getToken());
        redisTemplate.opsForValue().set(userTokenKey, user, expire, TimeUnit.MILLISECONDS);
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
                String accessTokenKey = getAccessTokenKey(accessToken);
                stringRedisTemplate.opsForValue().set(accessTokenKey, loginUser.getToken(),
                    super.getProperties().getExpireTime(), super.getProperties().getTimeUnit());
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
            String token = stringRedisTemplate.opsForValue().get(getAccessTokenKey(accessToken));
            token = StrUtil.isNotBlank(token) ? "" : token;
            stringRedisTemplate.delete(getUserTokenKey(token));
            stringRedisTemplate.delete(getAccessTokenKey(accessToken));
        }
    }

    @Override
    public Map<String, UserDetails> getOnline() {
        Map<String, UserDetails> maps = Maps.newHashMap();
        try {

            Set<String> keys = stringRedisTemplate.keys(getAccessTokenKey("*"));
            if (CollectionUtils.isEmpty(keys)) {
                return maps;
            }
            for (String key : keys) {
                String accessToken = org.apache.commons.lang3.StringUtils.remove(key, LOGIN_TOKEN_KEY_PREFIX);
                String tokenKey = stringRedisTemplate.opsForValue().get(getAccessTokenKey(accessToken));
                if (StrUtil.isNotBlank(tokenKey)) {
                    User cacheUser = redisTemplate.opsForValue().get(getUserTokenKey(tokenKey));
                    maps.put(accessToken, cacheUser);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maps;
    }
}
