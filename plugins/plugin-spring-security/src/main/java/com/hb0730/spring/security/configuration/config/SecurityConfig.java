package com.hb0730.spring.security.configuration.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * security 配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "zoom.security")
public class SecurityConfig {
    /**
     * 匿名访问
     */
    private List<String> anonymousUrls;

}

