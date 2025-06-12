package com.hb0730.spring.security.core.strategy;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

/**
 * 静态资源 认证策略
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
public class StaticResourceAuthorizeRequestsCustomizer extends AuthorizeRequestsCustomizer {
    @Override
    public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry
                // 静态资源
                .requestMatchers(HttpMethod.GET, "/css/**", "/js/**", "/images/**", "/webjars/**", "/*/favicon.ico").permitAll();
    }
}
