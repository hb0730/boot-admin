package com.hb0730.boot.admin.message.mail;

import lombok.Data;
import lombok.ToString;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring mail properties
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/9
 */
@Data
@ToString
public class SpringMailProperties {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    /**
     * SMTP server host. For instance, `smtp.example.com`.
     */
    private String host;
    /**
     * SMTP server port.
     */
    private Integer port;

    /**
     * Login user of the SMTP server.
     */
    private String username;

    /**
     * Login password of the SMTP server.
     */
    private String password;

    /**
     * Protocol used by the SMTP server.
     */
    private String protocol = "smtp";

    /**
     * Default MimeMessage encoding.
     */
    private Charset defaultEncoding = DEFAULT_CHARSET;

    /**
     * Additional JavaMail Session properties.
     */
    private Map<String, String> properties = new HashMap<>();

    /**
     * Session JNDI name. When set, takes precedence over other Session settings.
     */
    private String jndiName;

    public SpringMailProperties(boolean needDebug) {
        addProperties("mail.debug", String.valueOf(needDebug));
        addProperties("mail.smtp.auth", Boolean.TRUE.toString());
        addProperties("mail.smtp.ssl.enable", Boolean.TRUE.toString());
        addProperties("mail.smtp.timeout", "10000");
    }

    public void addProperties(String key, String value) {
        if (null == this.properties) {
            properties = new HashMap<>();
        }
        this.properties.put(key, value);
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

}
