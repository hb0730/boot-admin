package com.hb0730.boot.admin.message.mail;

import org.springframework.lang.NonNull;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Properties;

/**
 * create {@link  JavaMailSender}
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/9
 */
public class SpringMailSenderFactory {

    /**
     * 获取{@link JavaMailSender}
     *
     * @param properties mail 配置
     * @return {@link JavaMailSender}
     */
    public static JavaMailSender getMailSender(@NonNull SpringMailProperties properties) {
        Assert.notNull(properties, "Mail properties must not be null");
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        setProperties(mailSender, properties);
        return mailSender;
    }

    private static void setProperties(@NonNull JavaMailSenderImpl mailSender,
                                      @NonNull SpringMailProperties springMailProperties) {
        mailSender.setHost(springMailProperties.getHost());
        mailSender.setPort(springMailProperties.getPort());
        mailSender.setUsername(springMailProperties.getUsername());
        mailSender.setPassword(springMailProperties.getPassword());
        mailSender.setProtocol(springMailProperties.getProtocol());
        mailSender.setDefaultEncoding(springMailProperties.getDefaultEncoding().name());

        if (!CollectionUtils.isEmpty(springMailProperties.getProperties())) {
            Properties properties = new Properties();
            properties.putAll(springMailProperties.getProperties());
            mailSender.setJavaMailProperties(properties);
        }
    }
}
