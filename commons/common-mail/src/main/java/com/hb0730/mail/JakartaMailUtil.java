package com.hb0730.mail;

import com.hb0730.base.utils.StrUtil;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.internet.MimeUtility;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Date;
import java.util.Properties;

/**
 * Jakarta Mail邮件工具类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/1
 * @see <a href="https://jakarta.ee/specifications/mail/">Jakarta Mail API</a>
 */
@Slf4j
public class JakartaMailUtil {
    /**
     * 邮件服务器地址
     */
    @Setter
    private static String host;
    /**
     * 邮件服务器端口
     */
    @Setter
    private static Integer port = 25;
    /**
     * 发送邮件的邮箱
     */
    @Setter
    private static String username;
    /**
     * 发送邮件的密码
     */
    @Setter
    private static String password;
    /**
     * 发送邮件的协议 smtp, pop3, imap
     */
    @Setter
    private static String protocol = "smtp";

    /**
     * 发送邮件方
     */
    @Setter
    private static String fromAddress;
    /**
     * 发送邮件方-名称
     */
    @Setter
    private static String fromName;


    /**
     * 发送邮件
     *
     * @param receiver 消息接收人
     * @param subject  主题
     * @param content  内容
     */
    public static void send(final String receiver, final String subject, final String content) {
        send(receiver, null, subject, content);
    }

    /**
     * 发送邮件
     *
     * @param receiver 消息接收人
     * @param cc       抄送人
     * @param subject  主题
     * @param content  内容
     */
    public static void send(final String receiver, final String cc, final String subject, final String content) {
        // 发送邮件
        Properties props = new Properties();
        props.put("mail.smtp.host", JakartaMailUtil.host);
        props.put("mail.smtp.port", JakartaMailUtil.port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.timeout", "20000");
        props.put("mail.transport.protocol", JakartaMailUtil.protocol);
        // 创建会话
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(JakartaMailUtil.username, JakartaMailUtil.password);
            }
        };
        Session session = Session.getInstance(props, auth);
        // message
        try {
            Message message = new MimeMessage(session);
            // 设置发送方邮箱地址
            InternetAddress formAddress = new InternetAddress(JakartaMailUtil.fromAddress);
            formAddress.setPersonal(MimeUtility.decodeText(JakartaMailUtil.fromName));
            message.setFrom(formAddress);
            // 设置接收方邮箱地址
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            // 设置抄送方邮箱地址
            if (StrUtil.isNotBlank(cc)) {
                message.setRecipient(Message.RecipientType.CC, new InternetAddress(cc));
            }
            //标题
            message.setSubject(subject);
            message.setSentDate(new Date());
            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            MimeMultipart multipart = new MimeMultipart("mixed");
            message.setContent(multipart);
            // 添加邮件正文
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(content, "text/html;charset=UTF-8");
            multipart.addBodyPart(htmlPart);
            // 保存邮件
            message.saveChanges();
            // 发送邮件
            Transport.send(message);
            log.info("发送邮件成功 {} : {} -> {}", subject, content, receiver);
        } catch (Exception e) {
            log.warn("发送邮件失败 {} : {} -> {}", subject, content, receiver);
            log.error("发送邮件失败", e);
        }
    }

    /**
     * 发送邮件
     *
     * @param receiver 消息接收人
     * @param subject  主题
     * @param content  内容
     * @param file     附件
     * @param fileName 附件名称
     */
    public static void send(final String receiver, final String subject, final String content,
                            File file, String fileName) {
        send(receiver, null, subject, content, file, fileName);
    }

    /**
     * 发送邮件
     *
     * @param receiver 消息接收人
     * @param cc       抄送人
     * @param subject  主题
     * @param content  内容
     * @param file     附件
     * @param fileName 附件名称
     */
    public static void send(final String receiver, final String cc, final String subject, final String content,
                            File file, String fileName) {
        // 发送邮件
        Properties props = new Properties();
        props.put("mail.smtp.host", JakartaMailUtil.host);
        props.put("mail.smtp.port", JakartaMailUtil.port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.timeout", "20000");
        props.put("mail.transport.protocol", JakartaMailUtil.protocol);
        // 创建会话
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(JakartaMailUtil.username, JakartaMailUtil.password);
            }
        };
        Session session = Session.getInstance(props, auth);
        // message
        try {
            Message message = new MimeMessage(session);
            // 设置发送方邮箱地址
            InternetAddress formAddress = new InternetAddress(JakartaMailUtil.fromAddress);
            formAddress.setPersonal(MimeUtility.decodeText(JakartaMailUtil.fromName));
            message.setFrom(formAddress);
            // 设置接收方邮箱地址
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            // 设置抄送方邮箱地址
            if (StrUtil.isNotBlank(cc)) {
                message.setRecipient(Message.RecipientType.CC, new InternetAddress(cc));
            }
            //标题
            message.setSubject(subject);
            message.setSentDate(new Date());
            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            MimeMultipart multipart = new MimeMultipart("mixed");
            message.setContent(multipart);
            // 添加邮件正文
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(content, "text/html;charset=UTF-8");
            multipart.addBodyPart(htmlPart);
            // 添加附件
            MimeBodyPart filePart = new MimeBodyPart();
            filePart.attachFile(file);
            filePart.setFileName(fileName);
            multipart.addBodyPart(filePart);
            // 保存邮件
            message.saveChanges();
            // 发送邮件
            Transport.send(message);
            log.info("发送邮件成功 {} : {} -> {}", subject, content, receiver);
        } catch (Exception e) {
            log.warn("发送邮件失败 {} : {} -> {}", subject, content, receiver);
            log.error("发送邮件失败", e);
        }
    }


}