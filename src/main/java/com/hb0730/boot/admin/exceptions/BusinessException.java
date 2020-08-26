package com.hb0730.boot.admin.exceptions;

/**
 * 业务异常
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
