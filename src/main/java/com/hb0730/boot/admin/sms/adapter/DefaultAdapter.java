package com.hb0730.boot.admin.sms.adapter;

import com.hb0730.boot.admin.sms.properties.AliyunSmsProperties;
import com.hb0730.boot.admin.sms.properties.SmsProperties;
import com.hb0730.boot.admin.sms.service.AbstractSmsService;
import com.hb0730.boot.admin.sms.service.impl.AliyunSmsServiceImpl;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 模式适配
 *
 * @author bing_huang
 * @date 2020/07/03 11:11
 * @since V1.0
 */
@Component
public class DefaultAdapter implements SmsAdapter {
    private static final String DEFAULT = "default";

    @Override
    public AbstractSmsService getInterceptorImpl(SmsProperties properties) {
        return new AliyunSmsServiceImpl((AliyunSmsProperties) properties);
    }

    @Override
    public boolean supportsSms(@NonNull String impl) {
        Assert.hasText(impl, "type impl can not be null");
        return StringUtils.equals(DEFAULT, impl);
    }
}
