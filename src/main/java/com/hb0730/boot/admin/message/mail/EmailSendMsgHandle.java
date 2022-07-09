package com.hb0730.boot.admin.message.mail;

import cn.hutool.core.util.StrUtil;
import com.hb0730.boot.admin.exceptions.BusinessException;
import com.hb0730.boot.admin.message.BootAdminMsg;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/9
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class EmailSendMsgHandle implements BootAdminMsg {
    @Getter
    @Setter
    private JavaMailSender javaMailSender;
    /**
     * email fromName
     */
    @Setter
    @Getter
    private String fromName;

    @Override
    public void sendMsg(String receiver, String title, String content) {
        log.info("开始发送邮件 {} : {} -> {}", receiver, title, content);
        try {
            if (null == javaMailSender) {
                throw new BusinessException("请Set JavaMailSender");
            }
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            String username = ((JavaMailSenderImpl) javaMailSender).getUsername();
            String from = username;
            if (StrUtil.isNotBlank(this.fromName)) {
                from = String.format("%s <%s>", fromName, username);
            }
            message.setFrom(from);
            messageHelper.setTo(receiver);
            messageHelper.setSubject(title);
            messageHelper.setText(content, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("邮件发送失败 {} : {} -> {}", receiver, title, content);
        }
    }
}
