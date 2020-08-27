package com.hb0730.boot.admin.token;

import com.google.common.collect.Maps;
import com.hb0730.boot.admin.token.configuration.TokenProperties;
import com.hb0730.commons.lang.StringUtils;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 抽象
 *
 * @author bing_huang
 * @since 3.0.0
 */
public abstract class AbstractTokenService implements ITokenService {
    @Getter
    @Setter
    private TokenProperties properties;

    public AbstractTokenService(TokenProperties properties) {
        this.properties = properties;
    }

    /**
     * 将AccessToken转换成redis key
     *
     * @param accessToken accessToken
     * @return {@link #LOGIN_TOKEN_KEY_PREFIX}+accessToken
     */
    protected String getAccessTokenKey(String accessToken) {
        return LOGIN_TOKEN_KEY_PREFIX + accessToken;
    }

    /**
     * 将 token 转换成 redis key
     * @param key token
     * @return {@link #LOGIN_USER_KEY_PREFIX}+key
     */
    protected String getUserTokenKey(String key) {
        return LOGIN_USER_KEY_PREFIX + key;
    }

    /**
     * 根据请求获取AccessToken
     *
     * @param request 请求
     * @return AccessToken
     */
    protected String getAccessToken(HttpServletRequest request) {
        String token = request.getHeader("");
        // 校验是否满足前缀
        if (StringUtils.isNotBlank(token) && token.startsWith(this.properties.getTokenPrefix())) {
            token = token.replace(this.properties.getTokenPrefix(), "");
        }
        return token;
    }

    /**
     * <p>
     * 生成token
     * </p>
     *
     * @return token令牌
     */
    protected String extractKey(String key) {
        Map<String, String> maps = Maps.newHashMap();
        maps.put("secret", properties.getSecret());
        maps.put(LOGIN_USER_KEY_PREFIX, key);
        return createToken(maps);
    }
}
