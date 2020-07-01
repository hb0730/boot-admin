package com.hb0730.boot.admin.mail.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

/**
 * email 配置
 *
 * @author bing_huang
 * @date 2020/07/01 13:09
 * @since V1.0
 */
@Data
@ToString
@Configuration
public class EmailProperties {
    /**
     * 发送主机
     */
    private String host;

    /**
     * 协议
     */
    private String protocol;

    /**
     * ssl 端口
     */
    private Integer sslPort;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 发件人
     */
    private String fromName;

    /**
     *是否启用
     */
    private Boolean enabled;

}
