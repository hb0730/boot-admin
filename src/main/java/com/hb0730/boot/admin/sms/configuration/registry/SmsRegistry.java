package com.hb0730.boot.admin.sms.configuration.registry;

import com.hb0730.boot.admin.sms.configuration.SmsAutoConfigure;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * sms 装配
 *
 * @author bing_huang
 * @date 2020/07/01 10:49
 * @since V1.0
 */
@FunctionalInterface
public interface SmsRegistry {
    /**
     * 存储
     *
     * @see SmsAutoConfigure#smsService()
     * @see com.hb0730.boot.admin.sms.configuration.factory.SmsFactory#addService(Map)
     */
    void registry();
}
