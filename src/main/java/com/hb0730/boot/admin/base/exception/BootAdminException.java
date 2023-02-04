package com.hb0730.boot.admin.base.exception;

/**
 * 系统异常
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/30
 */
public class BootAdminException extends RuntimeException {
    public BootAdminException() {
        super();
    }

    public BootAdminException(String message) {
        super(message);
    }

    public BootAdminException(String message, Throwable cause) {
        super(message, cause);
    }

    public BootAdminException(Throwable cause) {
        super(cause);
    }

    protected BootAdminException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
