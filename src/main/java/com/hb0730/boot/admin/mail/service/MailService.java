package com.hb0730.boot.admin.mail.service;

import java.util.Map;

/**
 * mail 接口
 *
 * @author bing_huang
 * @date 2020/07/01 13:01
 * @since V1.0
 */
public interface MailService {

    /**
     * 发送文本
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendTextMail(String to, String subject, String content);

    /**
     * HTML发送
     *
     * @param to           收件人
     * @param subject      主题
     * @param content      内容
     * @param templateName 模板名称
     */
    void sendTemplateMail(String to, String subject, Map<String, Object> content, String templateName);

    /**
     * 附件发送
     *
     * @param to             收件人
     * @param subject        主题
     * @param content        内容
     * @param templateName   模板名称
     * @param attachFilePath 附件全路径
     */
    void sendAttachMail(String to, String subject, Map<String, Object> content, String templateName, String attachFilePath);

    /**
     * 测试连接
     */
    void testConnection();
}
