package com.hb0730.spring.security.core.strategy;

import com.hb0730.base.utils.CollectionUtil;
import com.hb0730.spring.security.configuration.config.SecurityConfig;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

import java.util.List;

/**
 * 配置文件 认证策略
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
public class ConfigAuthorizeRequestsCustomizer extends AuthorizeRequestsCustomizer {
    private final SecurityConfig securityConfig;

    public ConfigAuthorizeRequestsCustomizer(SecurityConfig securityConfig) {
        this.securityConfig = securityConfig;
    }

    @Override
    public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        // 匿名访问
        List<String> anonymousUrls = securityConfig.getAnonymousUrls();
        if (CollectionUtil.isNotEmpty(anonymousUrls)) {
            registry.requestMatchers(anonymousUrls.toArray(new String[0])).permitAll();
        }
    }
}
