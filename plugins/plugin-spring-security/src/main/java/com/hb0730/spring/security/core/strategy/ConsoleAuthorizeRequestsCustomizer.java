package com.hb0730.spring.security.core.strategy;

import com.hb0730.base.utils.StrUtil;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

/**
 * 控制台 认证策略
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
public class ConsoleAuthorizeRequestsCustomizer extends AuthorizeRequestsCustomizer {

    private final String adminSeverContextPath;

    private final String managementEndpoints;

    public ConsoleAuthorizeRequestsCustomizer(String adminSeverContextPath, String managementEndpoints) {
        this.adminSeverContextPath = adminSeverContextPath;
        this.managementEndpoints = managementEndpoints;
    }

    @Override
    public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry
                // swagger 接口文档
                .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                .requestMatchers("/swagger-resources/**", "/webjars/**", "/api-docs/**", "/*/api-docs").anonymous()
                // druid 监控
                .requestMatchers("/druid/**").anonymous()
                // actuator 端点
                .requestMatchers("/actuator/**").anonymous()
        ;
        if (StrUtil.isNotBlank(adminSeverContextPath)) {
            registry.requestMatchers(adminSeverContextPath, adminSeverContextPath + "/**").permitAll();
        }
        if (StrUtil.isNotBlank(managementEndpoints)) {
            registry.requestMatchers(managementEndpoints, managementEndpoints + "/**").permitAll();
        }
    }
}
