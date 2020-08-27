package com.hb0730.boot.admin.configuration.properties;

import com.hb0730.boot.admin.token.configuration.TokenProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * boot admin 配置
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "boot.admin")
public class BootAdminProperties {
    /**
     * 项目名称
     */
    private String name;
    /**
     * 版本
     */
    private String version;

    /**
     * 版权年份
     */
    private String copyrightYear;

    /**
     * 实例演示开关
     */
    private boolean demoEnabled = false;

    /**
     * 获取地址
     */
    private boolean addressEnabled = false;

    private TokenProperties tokenConfig = new TokenProperties();
}
