package com.hb0730.boot.admin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * 跨域
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Configuration
public class CorsConfiguration {

    /**
     * 创建跨域filter
     *
     * @return {@link CorsFilter}
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource corsConfiguration = new UrlBasedCorsConfigurationSource();
        corsConfiguration.registerCorsConfiguration("/**", build());
        return new CorsFilter(corsConfiguration);
    }

    private org.springframework.web.cors.CorsConfiguration build() {
        org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();
        configuration.setAllowCredentials(true);
        // 允许访问的客户端域名
        //        configuration.addAllowedOrigin("*");
        // (springboot2.4以上的加入这一段可解决 allowedOrigins cannot contain the special value "*"问题)
        List<String> allowedOriginPatterns = new ArrayList<>();
        allowedOriginPatterns.add("*");
        configuration.setAllowedOriginPatterns(allowedOriginPatterns);

        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addExposedHeader("content-disposition");
        configuration.setMaxAge(3600L);
        return configuration;
    }

}
