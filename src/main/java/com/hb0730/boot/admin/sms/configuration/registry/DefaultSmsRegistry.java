package com.hb0730.boot.admin.sms.configuration.registry;

import com.hb0730.boot.admin.sms.configuration.factory.SmsFactory;
import com.hb0730.boot.admin.sms.constant.SmsTypeEnum;
import com.hb0730.boot.admin.sms.model.properties.SmsProperties;
import com.hb0730.boot.admin.sms.service.ISmsService;
import com.hb0730.boot.admin.sms.service.impl.AliyunSmsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 默认
 *
 * @author bing_huang
 * @date 2020/07/01 10:51
 * @since V1.0
 */
@AllArgsConstructor
@Component
public class DefaultSmsRegistry implements SmsRegistry {
    private final SmsFactory factory;
    private final SmsProperties properties;

    @Override
    public void registry() {
        ISmsService smsService = new AliyunSmsServiceImpl(properties);
        factory.addService(SmsTypeEnum.ALIYUN, smsService);
    }
}
