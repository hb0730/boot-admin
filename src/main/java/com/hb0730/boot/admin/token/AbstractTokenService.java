package com.hb0730.boot.admin.token;

import cn.hutool.extra.servlet.ServletUtil;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.security.model.User;
import com.hb0730.boot.admin.token.configuration.TokenProperties;
import com.hb0730.commons.lang.StringUtils;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
     * 根据请求获取AccessToken
     *
     * @param request 请求
     * @return AccessToken
     */
    protected String getAccessToken(HttpServletRequest request) {
        String token = request.getHeader(this.properties.getHeader());
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
        Map<String, Object> maps = Maps.newHashMap();
        maps.put(LOGIN_USER_KEY_PREFIX, key);
        return createToken(maps, this.properties.getSecret());
    }

    /**
     * 设置用户代理信息
     *
     * @param user 登录信息
     */
    protected void setUserAgent(User user) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (null == attributes) {
            return;
        }
        if (attributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            String ip = ServletUtil.getClientIP(request);
            user.setIpaddr(ip);
            user.setBrowser(userAgent.getBrowser().getName());
            user.setOs(userAgent.getOperatingSystem().getName());
        }
    }
}
