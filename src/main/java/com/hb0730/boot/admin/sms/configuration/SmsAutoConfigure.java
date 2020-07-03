package com.hb0730.boot.admin.sms.configuration;

import com.hb0730.boot.admin.configuration.properties.BootAdminProperties;
import com.hb0730.boot.admin.sms.adapter.SmsAdapter;
import com.hb0730.boot.admin.sms.properties.SmsProperties;
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
 * @date 2020/07/03 10:50
 * @since V1.0
 */
@Configuration
@AllArgsConstructor
public class SmsAutoConfigure {
    private final ApplicationContext context;
    private final BootAdminProperties properties;

    @Bean
    public ISmsService smsService() {
        Map<String, SmsAdapter> beans = context.getBeansOfType(SmsAdapter.class);
        SmsAdapter adapter = beans.values()
                .stream()
                .filter(e -> e.supportsSms(properties.getSmsImpl()))
                .findFirst()
                .orElseThrow();
        return adapter.getInterceptorImpl(getProperties());
    }

    /**
     * 获取对应的properties
     *
     * @return Sms properties
     */
    private SmsProperties getProperties() {
        Map<String, SmsProperties> beans = context.getBeansOfType(SmsProperties.class);
        return beans.values()
                .stream().
                        filter(e -> e.supportType(properties.getSmsType()))
                .findFirst()
                .orElseThrow();
    }
}
