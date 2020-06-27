package com.hb0730.boot.admin.security.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.hb0730.boot.admin.commons.constant.RedisConstants;
import com.hb0730.boot.admin.commons.constant.enums.TokenTypeEnum;
import com.hb0730.boot.admin.commons.utils.json.GsonUtils;
import com.hb0730.boot.admin.configuration.properties.BootAdminProperties;
import com.hb0730.boot.admin.security.model.LoginUser;
import com.hb0730.boot.admin.security.service.AbstractTokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * token验证处理
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class InMemoryTokenServiceImpl extends AbstractTokenService {
    @CreateCache(cacheType = CacheType.LOCAL, area = RedisConstants.REDIS_JETCACHE_AREA, name = RedisConstants.REDIS_JETCACHE_NAME_LOGIN)
    private Cache<String, Object> loginCache;

    private final Long expire;
    private final Long refreshTime;
    private final BootAdminProperties properties;

    public InMemoryTokenServiceImpl(BootAdminProperties properties) {
        super(properties);
        this.properties = properties;
        expire = TimeUnit.MILLISECONDS.convert(properties.getTokenConfig().getExpireTime(), properties.getTokenConfig().getTimeUnit());
        refreshTime = TimeUnit.MILLISECONDS.convert(properties.getTokenConfig().getRefreshTime(), properties.getTokenConfig().getTimeUnit());
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
//        accessTokenStore.put(newAccessToken, token, expire, TimeUnit.MILLISECONDS);
        loginCache.put(newAccessToken, token);
        // 返回 登录令牌
        return accessToken;
    }

    @Override
    public void refreshAccessToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expire);
        //根据token缓存
        String userTokenKey = getUserTokenKey(loginUser.getToken());
        //        authenticationStore.put(tokenKey, loginUser);
        loginCache.put(userTokenKey, loginUser);
    }

    @Override
    public void verifyAccessToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= refreshTime) {
            refreshAccessToken(loginUser);
        }
    }

    @Override
    public void verifyAccessToken(HttpServletRequest request) {
        LoginUser loginUser = getLoginUser(request);
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= refreshTime) {
            refreshAccessToken(loginUser);
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
//        if (accessTokenStore.size() > 0) {
//            for (Map.Entry<String, String> entry : accessTokenStore.entrySet()) {
//                String key = entry.getKey();
//                maps.put(key, authenticationStore.get(getUserTokenKey(entry.getValue())));
//            }
//            return maps;
//        }
        return null;
    }

    @Override
    public boolean supportType(TokenTypeEnum type) {
        return TokenTypeEnum.LOCAL.equals(type);
    }
}
