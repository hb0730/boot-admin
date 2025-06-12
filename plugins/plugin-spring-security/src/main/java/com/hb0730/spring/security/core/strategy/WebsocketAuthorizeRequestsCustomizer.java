package com.hb0730.spring.security.core.strategy;

import com.hb0730.base.utils.StrUtil;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

/**
 * websocket 认证策略
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
public class WebsocketAuthorizeRequestsCustomizer extends AuthorizeRequestsCustomizer {
    private final String prefix;

    public WebsocketAuthorizeRequestsCustomizer(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {

        if (StrUtil.isBlank(prefix)) {
            return;
        }
        registry
                .requestMatchers(prefix + "/**").permitAll();
    }
}
