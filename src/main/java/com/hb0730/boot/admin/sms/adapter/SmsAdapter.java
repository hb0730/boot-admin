package com.hb0730.boot.admin.sms.adapter;

import com.hb0730.boot.admin.sms.properties.SmsProperties;
import com.hb0730.boot.admin.sms.service.AbstractSmsService;

/**
 * 适配,实现需要被spring管理bean
 *
 * @author bing_huang
 * @date 2020/07/03 11:07
 * @see DefaultAdapter
 * @since V1.0
 */
public interface SmsAdapter {
    /**
     * 获取 sms实现
     *
     * @param properties 配置
     * @return sms 实现
     */
    AbstractSmsService getInterceptorImpl(SmsProperties properties);

    /**
     * 是否为支持的类型
     *
     * @param impl 类型
     * @return true 是
     */
    boolean supportsSms(String impl);
}
