package com.hb0730.web.configure.cors;

import com.hb0730.base.pool.StrPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 跨域配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/3/21
 */
@AutoConfiguration
@EnableConfigurationProperties(CorsProperties.class)
@ConditionalOnWebApplication // web应用
@ConditionalOnProperty(prefix = "zoom.api.cors", name = "enabled", havingValue = "true")
@Slf4j
public class CorsAutoConfiguration {

    /**
     * 配置跨域
     */
    @Bean
    @ConditionalOnBean
    public CorsConfigurationSource corsConfigurationSource(CorsProperties properties) {
        // 跨域配置
        CorsConfiguration config = new CorsConfiguration();

        // 设置跨域允许时间
        config.setMaxAge(3600L);
        // 配置允许跨域的域名
        if (properties.getAllowedOrigins().contains(StrPool.ASTERISK)) {
            config.addAllowedOriginPattern(StrPool.ASTERISK);
        } else {
            properties.getAllowedOrigins().forEach(config::addAllowedOrigin);
        }
        // 配置是否允许发送Cookie
        config.setAllowCredentials(true);
        // 配置允许跨域的请求方式
        properties.getAllowedMethods().forEach(config::addAllowedMethod);
        // 配置允许跨域的请求头
        properties.getAllowedHeaders().forEach(config::addAllowedHeader);
        // 配置允许跨域的响应头
        properties.getExposedHeaders().forEach(config::addExposedHeader);

        // 添加映射路径，拦截一切请求
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(StrPool.PATH_PATTERN, config);
        log.debug("[zoom cors]  Auto Configuration 'CorsConfigurationSource' completed initialization.");
        return source;
    }
}
