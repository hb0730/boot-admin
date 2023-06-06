package com.hb0730.boot.admin.config.cache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/11
 */
@Configuration
@ConfigurationProperties(prefix = "boot.admin")
@Data
public class BootAdminProperties {
    /**
     * 缓存
     */
    private CacheProperties cache = new CacheProperties();
    /**
     * 是否刷新路由,默认不刷新
     * <p>
     * 一般用于用户权限发生变化时，刷新路由，如果用户量过多，不建议启用
     * </p>
     */
    public Boolean refreshRoutes = false;

    @Data
    public static class CacheProperties {
        /**
         * 前缀
         */
        private String prefix = "boot:admin:cache";

    }
}
