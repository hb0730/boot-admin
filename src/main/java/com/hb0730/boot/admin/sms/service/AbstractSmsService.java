package com.hb0730.boot.admin.sms.service;

import com.hb0730.boot.admin.commons.constant.enums.SmsTypeEnum;
import com.hb0730.boot.admin.sms.properties.SmsProperties;
import org.springframework.lang.NonNull;

/**
 * 抽象sms服务
 *
 * @author bing_huang
 * @date 2020/07/03 10:56
 * @since V1.0
 */
public abstract class AbstractSmsService implements ISmsService {

    public AbstractSmsService(SmsProperties properties) {
    }

    /**
     * 获取对应的类型
     *
     * @return sms 类型
     */
    @NonNull
    protected abstract SmsTypeEnum supportType();
}
