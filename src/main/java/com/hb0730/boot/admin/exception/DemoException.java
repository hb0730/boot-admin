package com.hb0730.boot.admin.exception;

/**
 * @author bing_huang
 * @date 2020/06/27 13:41
 * @since V1.0
 */
public class DemoException extends BaseException {
    public DemoException(String module, String code, Object[] args, String defaultMessage) {
        super(module, code, args, defaultMessage);
    }

    public DemoException(String module, String code, Object[] args) {
        super(module, code, args);
    }

    public DemoException(String module, String defaultMessage) {
        super(module, defaultMessage);
    }

    public DemoException(String code, Object[] args) {
        super(code, args);
    }

    public DemoException(String defaultMessage) {
        super(defaultMessage);
    }
}
