package com.hb0730.boot.admin.token.configuration;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * token 配置
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode
@ToString
@Configuration
@ConfigurationProperties(prefix = "boot.admin.token-config")
public class TokenProperties {
    /**
     * 令牌自定义标识
     */
    private String header = "Authorization";
    /**
     * 令牌前缀
     */
    private String tokenPrefix = "Bearer ";

    /**
     * 过期时长
     */
    private Long expireTime = 30L;

    /**
     * 过期时长类型
     */
    private TimeUnit timeUnit = TimeUnit.DAYS;

    /**
     * 刷新时间
     */
    private Long refreshTime = 30L;

    /**
     * 加密
     */
    private String secret = "abcdefghijklmnopqrstuvwxyz";
}
