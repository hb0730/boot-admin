package com.hb0730.boot.admin.security.service;

import com.google.common.collect.Maps;
import com.hb0730.boot.admin.commons.constant.SecurityConstants;
import com.hb0730.boot.admin.commons.utils.ServletUtils;
import com.hb0730.boot.admin.security.model.LoginUser;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * token验证处理
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class TokenServiceImpl implements ITokenService {
    private final ConcurrentHashMap<String, String> accessTokenStore = new ConcurrentHashMap<String, String>();
    private final ConcurrentHashMap<String, LoginUser> authenticationStore = new ConcurrentHashMap<String, LoginUser>();
    /**
     * 令牌自定义标识
     */
    @Value("${token.header:Authorization}")
    private String header;

    /**
     * 令牌秘钥
     */
    @Value("${token.secret:abcdefghijklmnopqrstuvwxyz}")
    private String secret;

    /**
     * 令牌有效期（默认30分钟）
     */
    @Value("${token.expireTime:30}")
    private int expireTime;

    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            token = accessTokenStore.get(token);
            return authenticationStore.get(getTokenKey(token));
        }
        return null;
    }

    @Override
    public String createAccessToken(LoginUser loginUser) {
        String token = UUID.randomUUID().toString();
        loginUser.setToken(token);
        setUserAgent(loginUser);
        refreshAccessToken(loginUser);
        String accessToken = extractKey(token);
        accessTokenStore.put(accessToken, token);
        return accessToken;
    }

    @Override
    public void refreshAccessToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        //根据token缓存
        String tokenKey = getTokenKey(loginUser.getToken());
        authenticationStore.put(tokenKey, loginUser);
    }

    @Override
    public void verifyAccessToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshAccessToken(loginUser);
        }
    }

    @Override
    public void delLoginUser(HttpServletRequest request) {
        String accessToken = getToken(request);
        if (StringUtils.isNotBlank(accessToken)) {
            deleteAccessToken(accessToken);
        }
    }

    @Override
    public void deleteAccessToken(String accessToken) {
        if (StringUtils.isNotBlank(accessToken)) {
            String token = accessTokenStore.get(accessToken);
            authenticationStore.remove(getTokenKey(token));
            accessTokenStore.remove(accessToken);
        }
    }

    /**
     * 设置用户代理信息
     *
     * @param loginUser 登录信息
     */
    public void setUserAgent(LoginUser loginUser) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        loginUser.setBrowser(userAgent.getBrowser().getName());
        loginUser.setOs(userAgent.getOperatingSystem().getName());
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
        }
        return token;
    }

    private String getTokenKey(String uuid) {
        return SecurityConstants.LOGIN_TOKEN_KEY + uuid;
    }

    /**
     * <p>
     * 生成token
     * </p>
     *
     * @return token令牌
     */
    private String extractKey(String key) {
        Map<String, String> maps = Maps.newHashMap();
        maps.put("secret", secret);
        maps.put(SecurityConstants.LOGIN_USER_KEY, key);
        return createToken(maps);
    }

    /**
     * 从数据声明生成令牌
     *
     * @param values 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, String> values) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(values.toString().getBytes(StandardCharsets.UTF_8));
            return String.format("%032x", new BigInteger(1, bytes));
        } catch (NoSuchAlgorithmException nsae) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).", nsae);
        }
    }
}
