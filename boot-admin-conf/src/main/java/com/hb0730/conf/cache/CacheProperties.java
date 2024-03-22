package com.hb0730.conf.cache;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/1
 */
@ConfigurationProperties(prefix = "boot.admin.cache")
@Setter
@Getter
public class CacheProperties {
    /**
     * 是否启用缓存
     */
    private boolean enabled = true;
    /**
     * 缓存前缀
     */
    private String prefix = "boot_admin";
}
