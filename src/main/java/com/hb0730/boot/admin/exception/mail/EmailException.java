package com.hb0730.boot.admin.exception.mail;

import com.hb0730.boot.admin.exception.BaseException;

/**
 * @author bing_huang
 * @date 2020/07/01 13:45
 * @since V1.0
 */
public class EmailException extends BaseException {
    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable e) {
        super(message, e);
    }
}
