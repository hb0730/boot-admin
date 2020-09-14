package com.hb0730.boot.admin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Configuration
public class CoreConfiguration {

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

    private CorsConfiguration build() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addExposedHeader("content-disposition");
        configuration.setMaxAge(3600L);
        return configuration;
    }

}
