package com.hb0730.boot.admin.exception.sms;

import com.hb0730.boot.admin.exception.BaseException;

/**
 * @author bing_huang
 * @date 2020/07/01 9:43
 * @since V1.0
 */
public class SmsException extends BaseException {
    private static final String SYS_TYPE_CONFIG_ERROR_MSG = "短信服务商信息配置错误";

    public SmsException() {
        super(SYS_TYPE_CONFIG_ERROR_MSG);
    }

    public SmsException(String message) {
        super(message);
    }

    public SmsException(String message, Throwable e) {
        super(message, e);
    }
}
