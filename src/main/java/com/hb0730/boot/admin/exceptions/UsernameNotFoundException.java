package com.hb0730.boot.admin.exceptions;

/**
 * 用户不存在
 *
 * @author bing_huang
 */
public class UsernameNotFoundException extends AbstractException {
    public UsernameNotFoundException(String message) {
        super(message);
    }

    public UsernameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
