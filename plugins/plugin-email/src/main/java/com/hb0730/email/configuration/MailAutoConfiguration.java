package com.hb0730.email.configuration;

import com.hb0730.email.configuration.config.MailProperties;
import com.hb0730.email.core.GlobalMailAccount;
import com.hb0730.email.core.MailAccount;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/12/12
 */
@AutoConfiguration
@EnableConfigurationProperties(MailProperties.class)
public class MailAutoConfiguration {


    /**
     * 邮件账户
     *
     * @param mailProperties 邮件配置
     * @return 邮件账户
     */
    @ConditionalOnProperty(prefix = "zoom.mail", name = "enable", havingValue = "true", matchIfMissing = true)
    @Bean
    public MailAccount mailAccount(MailProperties mailProperties) {
        MailAccount mailAccount = new MailAccount();
        // 设置发件人
        mailAccount.setFrom(mailProperties.getFrom());
        // 设置邮件服务器
        mailAccount.setHost(mailProperties.getHost());
        // 设置协议
        mailAccount.setProtocol(mailProperties.getProtocol().name().toLowerCase());
        // 设置端口
        mailAccount.setPort(mailProperties.getPort());
        // 设置用户名
        mailAccount.setUser(mailProperties.getUsername());
        // 设置密码
        mailAccount.setPass(mailProperties.getPassword());
        // 设置是否使用SSL
        mailAccount.setSslEnable(mailProperties.isSsl());
        // 设置超时
        mailAccount.setTimeout(mailProperties.getTimeout());
        // 设置连接超时
        mailAccount.setConnectionTimeout(mailProperties.getConnectionTimeout());
        mailAccount = mailAccount.defaultIfEmpty();
        // 设置全局邮件账户
        GlobalMailAccount.INSTANCE.setAccount(mailAccount);
        return mailAccount;
    }
}
