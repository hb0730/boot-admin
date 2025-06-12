package com.hb0730.configuration.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 认证配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/25
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "zoom.auth")
public class AuthenticationConfig {
    /**
     * 是否允许多端登录
     */
    private Boolean allowMultiDevice;

    /**
     * 是否允许凭证续签
     */
    private Boolean allowRefresh;

    /**
     * 凭证续签最大次数
     */
    private Integer refreshMaxCount;

    /**
     * 登录失败发送站内信阈值
     */
    private Integer loginFailedSendThreshold;

    /**
     * 登录失败锁定次数
     */
    private Integer loginFailedLockCount;

    /**
     * 登录失败锁定时间,单位秒
     */
    private Integer loginFailedLockTime;
}
