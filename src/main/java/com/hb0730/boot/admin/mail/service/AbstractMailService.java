package com.hb0730.boot.admin.mail.service;

import com.hb0730.boot.admin.exception.mail.EmailException;
import com.hb0730.boot.admin.mail.factory.MailSenderFactory;
import com.hb0730.boot.admin.mail.properties.EmailProperties;
import com.hb0730.boot.admin.mail.properties.MailProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.Assert;

import javax.annotation.Nullable;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author bing_huang
 * @date 2020/07/01 13:06
 * @since V1.0
 */
public abstract class AbstractMailService implements MailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMailService.class);
    private static final int DEFAULT_POOL_SIZE = 5;
    private final EmailProperties properties;
    private MailProperties cachedMailProperties;
    private String cacheFromName;
    private JavaMailSender cachedMailSender;

    public AbstractMailService(EmailProperties properties) {
        this.properties = properties;
    }

    /**
     * 线程池
     */
    @Nullable
    private ExecutorService executorService;

    @NonNull
    public ExecutorService getExecutorService() {
        if (this.executorService == null) {
            this.executorService = Executors.newFixedThreadPool(DEFAULT_POOL_SIZE);
        }
        return executorService;
    }

    public void setExecutorService(@Nullable ExecutorService executorService) {
        this.executorService = executorService;
    }

    /**
     * Test connection with email server.
     */
    @Override
    public void testConnection() {
        JavaMailSender javaMailSender = getMailSender();
        if (javaMailSender instanceof JavaMailSenderImpl) {
            JavaMailSenderImpl mailSender = (JavaMailSenderImpl) javaMailSender;
            try {
                mailSender.testConnection();
            } catch (MessagingException e) {
                throw new EmailException("无法连接到邮箱服务器，请检查邮箱配置.[" + e.getMessage() + "]", e);
            }
        }
    }

    /**
     * 发送模板消息
     *
     * @param callback 回调函数 不为空
     */
    protected void sendMailTemplate(@Nullable Callback callback) {
        if (callback == null) {
            LOGGER.info("Callback is null, skip to send email");
            return;
        }
        Boolean enabled = properties.getEnabled();
        if (!enabled) {
            // If disabled
            LOGGER.info("Email has been disabled by yourself, you can re-enable it through email settings on admin page.");
            return;
        }
        JavaMailSender mailSender = getMailSender();
        printMailConfig();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage());

        try {
            messageHelper.setFrom(getFromAddress(mailSender));
            callback.handle(messageHelper);
            MimeMessage mimeMessage = messageHelper.getMimeMessage();
            mailSender.send(mimeMessage);
            LOGGER.info("Sent an email to [{}] successfully, subject: [{}], sent date: [{}]",
                    Arrays.toString(mimeMessage.getAllRecipients()),
                    mimeMessage.getSubject(),
                    mimeMessage.getSentDate());
        } catch (Exception e) {
            e.printStackTrace();
            throw new EmailException("邮件发送失败，请检查 SMTP 服务配置是否正确", e);
        }
    }

    /**
     * 如果线程池是启用状态则发送模板消息
     *
     * @param tryToSync 是否异步
     * @param callback  回调
     */
    protected void sendMailTemplate(Boolean tryToSync, @NonNull Callback callback) {
        ExecutorService executorService = getExecutorService();
        if (tryToSync && executorService != null) {
            executorService.execute(() -> sendMailTemplate(callback));
        } else {
            sendMailTemplate(callback);
        }
    }

    /**
     * 获取mail sender
     *
     * @return mail sender
     */
    @NonNull
    private synchronized JavaMailSender getMailSender() {
        if (this.cachedMailSender == null) {
            MailSenderFactory mailSenderFactory = new MailSenderFactory();
            this.cachedMailSender = mailSenderFactory.getMailSender(getMailProperties());
        }
        return this.cachedMailSender;
    }

    /**
     * 获取接收地址
     *
     * @param javaMailSender java mail sender
     * @return 接收网络地址
     * @throws UnsupportedEncodingException 提供错误的字符编码时引发
     */
    private synchronized InternetAddress getFromAddress(@NonNull JavaMailSender javaMailSender) throws UnsupportedEncodingException {
        Assert.notNull(javaMailSender, "Java mail sender must not be null");
        if (StringUtils.isBlank(this.cacheFromName)) {
            this.cacheFromName = this.properties.getFromName();
        }
        if (javaMailSender instanceof JavaMailSenderImpl) {
            JavaMailSenderImpl mailSender = (JavaMailSenderImpl) javaMailSender;
            String username = mailSender.getUsername();
            return new InternetAddress(username, this.cacheFromName, mailSender.getDefaultEncoding());
        }
        throw new UnsupportedEncodingException("Unsupported java mail sender: " + javaMailSender.getClass().getName());
    }

    /**
     * 获取 Spring mail 配置
     *
     * @return mail Properties
     */
    @NonNull
    private synchronized MailProperties getMailProperties() {
        if (cachedMailProperties == null) {
            MailProperties properties = new MailProperties(LOGGER.isDebugEnabled());
            properties.setHost(this.properties.getHost());
            properties.setPort(this.properties.getSslPort());
            properties.setUsername(this.properties.getUsername());
            properties.setPassword(this.properties.getPassword());
            properties.setProtocol(this.properties.getProtocol());
            this.cachedMailProperties = properties;
        }
        return this.cachedMailProperties;
    }

    /**
     * Print mail configuration.
     */
    private void printMailConfig() {
        if (!LOGGER.isDebugEnabled()) {
            return;
        }

        // get mail properties
        MailProperties mailProperties = getMailProperties();
        LOGGER.debug(mailProperties.toString());
    }

    /**
     * 清除缓存
     */
    protected void clearCache() {
        this.cachedMailProperties = null;
        this.cacheFromName = null;
        this.cachedMailSender = null;
        LOGGER.debug("Cleared all mail caches");
    }

    /**
     * 回调
     */
    protected interface Callback {
        /**
         * Handle message set.
         *
         * @param messageHelper mime message helper
         * @throws Exception if something goes wrong
         */
        void handle(@NonNull MimeMessageHelper messageHelper) throws Exception;
    }
}
