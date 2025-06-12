package com.hb0730.cache.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 缓存配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/23
 */
@ConfigurationProperties(prefix = "zoom.cache")
@Getter
@Setter
public class CacheProperties {
    /**
     * 是否启用
     */
    private boolean enable = true;
    /**
     * 缓存类型
     */
    private TypeEnum type = TypeEnum.REDIS;
    /**
     * 缓存前缀
     */
    private String prefix = "zoom";

    public static enum TypeEnum {
        /**
         * 本地缓存
         */
        LOCAL,
        /**
         * Redis缓存
         */
        REDIS,
        /**
         * Ehcache缓存
         */
        EHCACHE,
        /**
         * Caffeine缓存
         */
        CAFFEINE,
        /**
         * Guava缓存
         */
        GUAVA,
    }
}
