package com.hb0730.boot.admin.project.notice.mail.service.impl;

import com.hb0730.boot.admin.commons.enums.EmailPropertiesEnum;
import com.hb0730.boot.admin.project.notice.mail.model.dto.MailDTO;
import com.hb0730.boot.admin.project.notice.mail.model.dto.MailTestDTO;
import com.hb0730.boot.admin.project.notice.mail.service.IMailService;
import com.hb0730.boot.admin.project.system.option.service.IOptionService;
import org.springframework.lang.NonNull;
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
public class MailServiceImpl implements IMailService {
    private final IOptionService optionService;

    public MailServiceImpl(IOptionService optionService) {
        this.optionService = optionService;
    }

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

    }
}
