package com.hb0730.boot.admin.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Jwt参数配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/3
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class SecurityProperties {
    /**
     * jwt加密密钥
     */
    private String jwtSecret = "ZmQ0ZGI5NjQ0MDQwY2I4MjMxY2Y3ZmI3MjdhN2ZmMjNhODViOTg1ZGE0NTBjMGM4NDA5NzYxMjdjOWMwYWRmZTBlZjlhNGY3ZTg4Y2U3YTE1ODVkZDU5Y2Y3OGYwZWE1NzUzNWQ2YjFjZDc0NGMxZWU2MmQ3MjY1NzJmNTE0MzI=";

    /**
     * 令牌有效期 此处单位/s，
     * 默认：2小时
     */
    private Integer tokenValidity = 2 * 60 * 60;
    /**
     * 自动延长token
     * 默认：true
     */
    private boolean delayToken = true;

    /**
     * token 续期检查,单位秒
     * 默认: 30分钟
     */
    private Integer tokenDetect = 60 * 30;
}
