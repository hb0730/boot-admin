package com.hb0730.base.exception;

/**
 * 异常
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/23
 */
public class BasicException extends RuntimeException {
    /**
     * @param message 消息
     */
    public BasicException(String message) {
        super(message);
    }

    /**
     * @param template 模板
     * @param args     参数
     */
    public BasicException(String template, Object... args) {
        super(String.format(template, args));
    }

    public BasicException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasicException(Throwable cause) {
        super(cause);
    }
}
