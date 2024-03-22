package com.hb0730.conf.mail;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/1
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "boot.admin.mail")
public class MailProperties {
    /**
     * 邮件服务器地址
     */
    private String host;
    /**
     * 邮件服务器端口
     */
    private Integer port = 25;
    /**
     * 发送邮件的邮箱
     */
    private String username;
    /**
     * 发送邮件的密码
     */
    private String password;
    /**
     * 发送邮件的协议 smtp, pop3, imap
     */
    private String protocol = "smtp";

    /**
     * 发送邮件方
     */
    private String fromAddress;
    /**
     * 发送邮件方-名称
     */
    private String fromName;
}
