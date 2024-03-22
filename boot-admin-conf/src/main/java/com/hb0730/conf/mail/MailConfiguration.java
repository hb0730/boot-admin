package com.hb0730.conf.mail;

import com.hb0730.base.utils.MailUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/1
 */
@EnableConfigurationProperties({MailProperties.class})
@org.springframework.context.annotation.Configuration
@ConditionalOnProperty(prefix = "boot.admin.mail", name = "enable", havingValue = "true")
public class MailConfiguration {
    public MailConfiguration(MailProperties properties) {
        MailUtil.setHost(properties.getHost());
        MailUtil.setPort(properties.getPort());
        MailUtil.setUsername(properties.getUsername());
        MailUtil.setPassword(properties.getPassword());
        MailUtil.setProtocol(properties.getProtocol());
        MailUtil.setFromAddress(properties.getFromAddress());
        MailUtil.setFromName(properties.getFromName());
    }
}
