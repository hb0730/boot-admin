package com.hb0730.email.configuration.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 邮件配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @link <a href="https://github.com/eclipse-ee4j/angus-mail">angus-mail 相关MailProperties</a>
 * @link <a href="https://github.com/jakartaee/mail-api">mail-api</a>
 * @date 2024/12/11
 */
@Data
@ConfigurationProperties(prefix = "zoom.mail")
public class MailProperties {
    /**
     * 是否启用
     */
    private boolean enable = true;
    /**
     * SMTP服务器域名
     */
    private String host;
    /**
     * 协议
     */
    private Protocol protocol = Protocol.SMTP;
    /**
     * SMTP服务端口,默认25,SSL为465
     */
    private int port = 465;
    /**
     * 发送方，遵循RFC-822标准"Personal Name &lt;user@host.domain&gt;"
     */
    private String from;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 使用 SSL安全连接
     */
    private boolean ssl = true;

    /**
     * SMTP超时时长，单位毫秒，缺省值不超时
     */
    private Long timeout = 0L;

    /**
     * Socket连接超时值，单位毫秒，缺省值不超时
     */
    private Long connectionTimeout = 0L;


    /**
     * 协议
     */
    public enum Protocol {
        SMTP,
        POP3,
        IMAP
    }
}
