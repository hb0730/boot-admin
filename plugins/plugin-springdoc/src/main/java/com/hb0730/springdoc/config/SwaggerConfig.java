package com.hb0730.springdoc.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/23
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "zoom.swagger")
public class SwaggerConfig {
    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 作者
     */
    private String author;

    /**
     * 版本
     */
    private String version;
    /**
     * url
     */
    private String url;

    /**
     * email
     */
    private String email;

    /**
     * license
     */
    private String license;

    /**
     * license-url
     */
    private String licenseUrl;

    /**
     * api 分组
     */
    private Map<String, GroupedApiConfig> groupedApi;

    @Data
    public static class GroupedApiConfig {

        /**
         * 名称
         */
        private String group;

        /**
         * 路径
         */
        private List<String> path = new ArrayList<>();

    }
}
