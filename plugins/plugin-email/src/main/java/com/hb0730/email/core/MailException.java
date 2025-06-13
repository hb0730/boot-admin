package com.hb0730.email.core;

/**
 * 邮件异常
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/12/12
 */
public class MailException extends RuntimeException {
    public MailException() {
    }

    public MailException(String message) {
        super(message);
    }

    public MailException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailException(Throwable cause) {
        super(cause);
    }
}
