package com.hb0730.boot.admin.config.cache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/11
 */
@Configuration
@ConfigurationProperties(prefix = "boot.admin.cache")
@Data
public class BootAdminCacheProperties {
    /**
     * 前缀
     */
    private String prefix = "boot:admin:cache";
}
