package com.hb0730.boot.admin.mail.factory;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.lang.NonNull;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Properties;

/**
 * Java mail sender factory.
 *
 * @author bing_huang
 * @date 2020/07/01 13:14
 * @since V1.0
 */
public class MailSenderFactory {

    /**
     * 获取java mail sender
     *
     * @param properties mail配置，不允许为空
     * @return java mail sender
     */
    @NonNull
    public JavaMailSender getMailSender(@NonNull MailProperties properties) {
        Assert.notNull(properties, "Mail properties must not be null");
        // create mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // set properties
        setProperties(mailSender, properties);

        return mailSender;
    }

    private void setProperties(@NonNull JavaMailSenderImpl mailSender, @NonNull MailProperties mailProperties) {
        mailSender.setHost(mailProperties.getHost());
        mailSender.setPort(mailProperties.getPort());
        mailSender.setUsername(mailProperties.getUsername());
        mailSender.setPassword(mailProperties.getPassword());
        mailSender.setProtocol(mailProperties.getProtocol());
        mailSender.setDefaultEncoding(mailProperties.getDefaultEncoding().name());

        if (!CollectionUtils.isEmpty(mailProperties.getProperties())) {
            Properties properties = new Properties();
            properties.putAll(mailProperties.getProperties());
            mailSender.setJavaMailProperties(properties);
        }
    }
}
