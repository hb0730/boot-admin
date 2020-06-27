package com.hb0730.boot.admin.security.service;

import com.google.common.collect.Maps;
import com.hb0730.boot.admin.commons.constant.SecurityConstants;
import com.hb0730.boot.admin.commons.utils.ServletUtils;
import com.hb0730.boot.admin.commons.utils.ip.IpUtils;
import com.hb0730.boot.admin.configuration.properties.BootAdminProperties;
import com.hb0730.boot.admin.security.model.LoginUser;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author bing_huang
 * @date 2020/06/27 10:52
 * @since V1.0
 */
public abstract class AbstractTokenService implements ITokenService {
    @Getter
    @Setter
    private BootAdminProperties properties;

    public AbstractTokenService(BootAdminProperties properties) {
        this.properties = properties;
    }

    /**
     * 获取用户令牌
     *
     * @param accessToken 令牌
     * @return {@link SecurityConstants#LOGIN_TOKEN_KEY}+accessToken
     */
    public String getAccessTokenKey(String accessToken) {
        return SecurityConstants.LOGIN_TOKEN_KEY + accessToken;
    }

    /**
     * 获取用户缓存的key
     *
     * @param key key
     * @return {@link SecurityConstants#LOGIN_USER_KEY}+key
     */
    public String getUserTokenKey(String key) {
        return SecurityConstants.LOGIN_USER_KEY + key;
    }

    /**
     * 获取请求token
     *
     * @param request 请求
     * @return token
     */
    public String getAccessToken(HttpServletRequest request) {
        String token = request.getHeader(properties.getTokenConfig().getHeader());
        if (StringUtils.isNotEmpty(token) && token.startsWith(properties.getTokenConfig().getPrefix())) {
            token = token.replace(properties.getTokenConfig().getPrefix(), "");
        }
        return token;
    }

    /**
     * 设置用户代理信息
     *
     * @param loginUser 登录信息
     */
    public void setUserAgent(LoginUser loginUser) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        loginUser.setIpaddr(ip);
        //用户地址
//        loginUser.setLoginLocation(AddressUtils.getrealaddressbyip(ip));
        loginUser.setBrowser(userAgent.getBrowser().getName());
        loginUser.setOs(userAgent.getOperatingSystem().getName());
    }

    /**
     * <p>
     * 生成token
     * </p>
     *
     * @return token令牌
     */
    public String extractKey(String key) {
        Map<String, String> maps = Maps.newHashMap();
        maps.put("secret", properties.getTokenConfig().getSecret());
        maps.put(SecurityConstants.LOGIN_USER_KEY, key);
        return createToken(maps);
    }
}
