package com.hb0730.boot.admin.configuration.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author bing_huang
 * @date 2020/07/28 7:47
 * @since V1.0
 */
@Data
@EqualsAndHashCode
@ToString
@ConfigurationProperties(prefix = "boot.admin.cache")
public class CacheProperties {
    private String cache = "memory";
}
