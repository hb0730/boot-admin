package com.hb0730.boot.admin.sms.configuration;

import com.hb0730.boot.admin.sms.configuration.factory.SmsFactory;
import com.hb0730.boot.admin.sms.configuration.registry.SmsRegistry;
import com.hb0730.boot.admin.sms.constant.SmsTypeEnum;
import com.hb0730.boot.admin.sms.model.properties.SmsProperties;
import com.hb0730.boot.admin.sms.service.ISmsService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 自动装配
 *
 * @author bing_huang
 * @date 2020/07/01 10:46
 * @since V1.0
 */
@Configuration
@AllArgsConstructor
public class SmsAutoConfigure {
    private final SmsProperties properties;
    private final ApplicationContext context;
    private final SmsFactory factory;

    /**
     * 获取指定的sms服务
     *
     * @return {@link ISmsService}
     */
    @Bean
    public ISmsService smsService() {
        SmsTypeEnum smsType = properties.getSmsType();
        Map<String, SmsRegistry> beans = context.getBeansOfType(SmsRegistry.class);
        beans.values().parallelStream().forEach(SmsRegistry::registry);
        return factory.getService(smsType);
    }
}
