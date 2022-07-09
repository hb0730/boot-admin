package com.hb0730.boot.admin.project.notice.mail.service.impl;

import com.hb0730.boot.admin.commons.enums.EmailPropertiesEnum;
import com.hb0730.boot.admin.message.BootAdminMsg;
import com.hb0730.boot.admin.message.mail.EmailSendMsgHandle;
import com.hb0730.boot.admin.message.mail.SpringMailProperties;
import com.hb0730.boot.admin.message.mail.SpringMailSenderFactory;
import com.hb0730.boot.admin.project.notice.mail.model.dto.MailDTO;
import com.hb0730.boot.admin.project.notice.mail.model.dto.MailTestDTO;
import com.hb0730.boot.admin.project.notice.mail.service.IMailService;
import com.hb0730.boot.admin.project.system.option.service.IOptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 邮箱服务实现
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MailServiceImpl implements IMailService {
    private final IOptionService optionService;
    private final BootAdminMsg emailSendMsgHandle;
    private JavaMailSender javaMailSender;

    @Override
    public boolean save(@NonNull MailDTO dto) {
        Assert.notNull(dto, "参数不为空");
        Map<String, Object> optionsMap = new HashMap<>();
        optionsMap.put(EmailPropertiesEnum.HOST.getValue(), dto.getHost());
        optionsMap.put(EmailPropertiesEnum.PROTOCOL.getValue(), dto.getProtocol());
        optionsMap.put(EmailPropertiesEnum.SSL_PORT.getValue(), dto.getSslPort());
        optionsMap.put(EmailPropertiesEnum.USERNAME.getValue(), dto.getUsername());
        optionsMap.put(EmailPropertiesEnum.PASSWORD.getValue(), dto.getPassword());
        optionsMap.put(EmailPropertiesEnum.FROM_NAME.getValue(), dto.getFromName());
        optionsMap.put(EmailPropertiesEnum.ENABLED.getValue(), dto.getEnabled());
        optionService.save(optionsMap);
        createJavaMailSend();
        return true;
    }

    @Override
    public MailDTO info() {
        MailDTO vo = new MailDTO();
        vo.setHost(optionService.getByPropertyOrDefault(EmailPropertiesEnum.HOST, String.class));
        vo.setProtocol(optionService.getByPropertyOrDefault(EmailPropertiesEnum.PROTOCOL, String.class));
        vo.setSslPort(optionService.getByPropertyOrDefault(EmailPropertiesEnum.SSL_PORT, Integer.class));
        vo.setUsername(optionService.getByPropertyOrDefault(EmailPropertiesEnum.USERNAME, String.class));
        vo.setPassword(optionService.getByPropertyOrDefault(EmailPropertiesEnum.PASSWORD, String.class));
        vo.setFromName(optionService.getByPropertyOrDefault(EmailPropertiesEnum.FROM_NAME, String.class));
        vo.setEnabled(optionService.getByPropertyOrDefault(EmailPropertiesEnum.ENABLED, Integer.class));
        return vo;
    }

    @Override
    public void test(@NonNull MailTestDTO dto) {
        emailSendMsgHandle.sendMsg(dto.getTo(), dto.getSubject(), dto.getContent());
    }

    private void createJavaMailSend() {
        MailDTO info = info();
        if (info.getEnabled() == 1) {

            SpringMailProperties mailProperties = new SpringMailProperties(log.isDebugEnabled());
            mailProperties.setPort(info.getSslPort());
            mailProperties.setPassword(info.getPassword());
            mailProperties.setProtocol(info.getProtocol());
            mailProperties.setUsername(info.getUsername());
            mailProperties.setHost(info.getHost());
            this.javaMailSender = SpringMailSenderFactory.getMailSender(mailProperties);
            ((EmailSendMsgHandle) emailSendMsgHandle).setJavaMailSender(javaMailSender);
        } else {
            this.javaMailSender = null;
            ((EmailSendMsgHandle) emailSendMsgHandle).setJavaMailSender(null);
        }
    }

    public JavaMailSender getJavaMailSender() {
        if (this.javaMailSender == null) {
            createJavaMailSend();
        }
        return this.javaMailSender;
    }
}
