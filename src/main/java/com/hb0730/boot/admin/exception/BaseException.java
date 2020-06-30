package com.hb0730.boot.admin.exception;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class BaseException extends AbstractBootAdminException {
    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private String code;

    private String message;

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(String module, String code, String message) {
        super(String.format("%s %s %s", code, module, message));
        this.module = module;
        this.code = code;
        this.message = message;
    }

    public BaseException(String module, String code, String message, Throwable e) {
        super(String.format("%s %s %s", code, module, message), e);
        this.module = module;
        this.code = code;
        this.message = message;
    }

    public BaseException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    public BaseException(String message, Object... args) {
        super(String.format(message, args));
        this.message = message;
    }

    public BaseException(String message, Throwable e, Object... args) {
        super(String.format(message, args), e);
        this.message = message;
    }


    @Override
    public String getStatus() {
        return this.code;
    }
}
