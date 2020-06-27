package com.hb0730.boot.admin.security.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.commons.constant.RedisConstants;
import com.hb0730.boot.admin.commons.constant.SecurityConstants;
import com.hb0730.boot.admin.commons.constant.enums.TokenTypeEnum;
import com.hb0730.boot.admin.commons.utils.json.GsonUtils;
import com.hb0730.boot.admin.configuration.properties.BootAdminProperties;
import com.hb0730.boot.admin.security.model.LoginUser;
import com.hb0730.boot.admin.security.service.AbstractTokenService;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * redis 缓存
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class RedisTokenServiceImpl extends AbstractTokenService {
    @CreateCache(cacheType = CacheType.REMOTE, area = RedisConstants.REDIS_JETCACHE_AREA, name = RedisConstants.REDIS_JETCACHE_NAME_LOGIN)
    private Cache<String, Object> loginCache;
    @Getter
    private final BootAdminProperties properties;

    public RedisTokenServiceImpl(BootAdminProperties properties) {
        super(properties);
        this.properties = properties;
    }

    @Override
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String accessToken = getAccessToken(request);
        if (StringUtils.isNotEmpty(accessToken)) {
            //用户令牌
            String accessTokenKey = getAccessTokenKey(accessToken);
            // 用户key
            String userTokenKey = String.valueOf(loginCache.get(accessTokenKey));

            return GsonUtils.gsonToBean(GsonUtils.json2String(loginCache.get(getUserTokenKey(userTokenKey))), LoginUser.class);
        }
        return null;
    }

    @Override
    public String createAccessToken(LoginUser loginUser) {
        String token = UUID.randomUUID().toString();
        // 用户 token
        loginUser.setToken(token);
        setUserAgent(loginUser);
        refreshAccessToken(loginUser);
        // 生成令牌
        String accessToken = extractKey(token);
        String newAccessToken = getAccessTokenKey(accessToken);
        // 存储登录token
        loginCache.put(newAccessToken, token, properties.getTokenConfig().getExpireTime(), properties.getTokenConfig().getTimeUnit());
        // 返回 登录令牌
        return accessToken;
    }

    @Override
    public void refreshAccessToken(LoginUser loginUser) {
        Long expire = TimeUnit.MILLISECONDS.convert(properties.getTokenConfig().getExpireTime(), properties.getTokenConfig().getTimeUnit());
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expire);
        //根据token缓存
        String userTokenKey = getUserTokenKey(loginUser.getToken());
        loginCache.put(userTokenKey, loginUser, expire, TimeUnit.MILLISECONDS);
    }

    @Override
    public void verifyAccessToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        long refreshTime = TimeUnit.MILLISECONDS.convert(properties.getTokenConfig().getRefreshTime(), properties.getTokenConfig().getTimeUnit());
        if (expireTime - currentTime <= refreshTime) {
            refreshAccessToken(loginUser);
        }
    }

    @Override
    public void verifyAccessToken(HttpServletRequest request) {
        LoginUser loginUser = getLoginUser(request);
        if (Objects.nonNull(loginUser)) {
            long expireTime = loginUser.getExpireTime();
            long currentTime = System.currentTimeMillis();
            long refreshTime = TimeUnit.MILLISECONDS.convert(properties.getTokenConfig().getRefreshTime(), properties.getTokenConfig().getTimeUnit());
            if (expireTime - currentTime <= refreshTime) {
                // 获取请求携带的令牌
                String accessToken = getAccessToken(request);
                //用户令牌
                String accessTokenKey = getAccessTokenKey(accessToken);
                // 存储登录token
                loginCache.put(accessTokenKey, loginUser.getToken(), properties.getTokenConfig().getExpireTime(), properties.getTokenConfig().getTimeUnit());
                refreshAccessToken(loginUser);
            }
        }
    }

    @Override
    public void delLoginUser(HttpServletRequest request) {
        String accessToken = getAccessToken(request);
        if (StringUtils.isNotBlank(accessToken)) {
            deleteAccessToken(accessToken);
        }
    }

    @Override
    public void deleteAccessToken(String accessToken) {
        if (StringUtils.isNotBlank(accessToken)) {
            String token = String.valueOf(loginCache.get(getAccessTokenKey(accessToken)));
            loginCache.remove(getUserTokenKey(token));
            loginCache.remove(getAccessTokenKey(accessToken));
        }
    }

    @Override
    public Map<String, UserDetails> getOnline() {
        Map<String, UserDetails> maps = Maps.newHashMap();
        RedisClient client = loginCache.unwrap(RedisClient.class);
        RedisAsyncCommands<String, String> commands = client.connect().async();

        RedisFuture<List<String>> keys = commands.keys(RedisConstants.REDIS_JETCACHE_NAME_LOGIN + SecurityConstants.LOGIN_TOKEN_KEY + "*");
        try {
            List<String> strings = keys.get();
            if (!CollectionUtils.isEmpty(strings)) {
                for (String string : strings) {
                    String accessToken = StringUtils.remove(string, RedisConstants.REDIS_JETCACHE_NAME_LOGIN + SecurityConstants.LOGIN_TOKEN_KEY);
                    String userKey = (String) loginCache.get(getAccessTokenKey(accessToken));
                    LoginUser loginUser = (LoginUser) loginCache.get(getUserTokenKey(userKey));
                    if (Objects.nonNull(loginUser)) {
                        maps.put(accessToken, loginUser);
                    }
                }
            }
            return maps;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean supportType(TokenTypeEnum type) {
        return TokenTypeEnum.REDIS.equals(type);
    }
}
