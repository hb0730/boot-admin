package com.hb0730.web.configure.cors;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 跨域配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/3/21
 */
@Data
@ConfigurationProperties(prefix = "zoom.api.cors")
public class CorsProperties {
    private static final List<String> ALL = List.of("*");
    /**
     * 是否启用跨域配置
     */
    private boolean enabled = false;

    /**
     * 允许跨域的域名
     */
    private List<String> allowedOrigins = new ArrayList<>(ALL);

    /**
     * 允许跨域的请求方式
     */
    private List<String> allowedMethods = new ArrayList<>(ALL);

    /**
     * 允许跨域的请求头
     */
    private List<String> allowedHeaders = new ArrayList<>(ALL);

    /**
     * 允许跨域的响应头
     */
    private List<String> exposedHeaders = new ArrayList<>();
}
