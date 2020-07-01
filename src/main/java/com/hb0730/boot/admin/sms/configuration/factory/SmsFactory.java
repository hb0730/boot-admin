package com.hb0730.boot.admin.sms.configuration.factory;

import com.hb0730.boot.admin.sms.configuration.SmsAutoConfigure;
import com.hb0730.boot.admin.sms.configuration.registry.DefaultSmsRegistry;
import com.hb0730.boot.admin.sms.configuration.registry.SmsRegistry;
import com.hb0730.boot.admin.sms.constant.SmsTypeEnum;
import com.hb0730.boot.admin.sms.service.ISmsService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * sms 集合
 *
 * @author bing_huang
 * @date 2020/07/01 10:40
 * @see SmsAutoConfigure#smsService()
 * @see SmsRegistry#registry()
 * @since V1.0
 */
@Component
public class SmsFactory {
    private final Map<SmsTypeEnum, ISmsService> services = new HashMap<>();

    @NonNull
    public ISmsService getService(@NonNull SmsTypeEnum type) {
        Assert.notNull(type, "smsTypeEnum can not be null");
        return services.get(type);
    }

    /**
     * 新增实现
     *
     * @param services sms服务
     * @return SmsFactory
     */
    @SuppressWarnings("UnusedReturnValue")
    @NonNull
    public SmsFactory addService(Map<SmsTypeEnum, ISmsService> services) {
        if (!CollectionUtils.isEmpty(services)) {
            this.services.putAll(services);
        }
        return this;
    }

    /**
     * 新增实现
     *
     * @param typeEnum 新增类型
     * @param service  sms服务
     * @return SmsFactory
     * @see DefaultSmsRegistry#registry()
     */
    @NonNull
    @SuppressWarnings("UnusedReturnValue")
    public SmsFactory addService(@NonNull SmsTypeEnum typeEnum, @NonNull ISmsService service) {
        Assert.notNull(typeEnum, "SmsTypeEnum can not be null");
        Assert.notNull(service, "service can not be null");
        this.services.put(typeEnum, service);
        return this;
    }
}
