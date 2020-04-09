package com.hb0730.boot.admin.exception;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class ExportException extends BaseException {

    public ExportException(String defaultMessage, Throwable e) {
        super(defaultMessage + ":" + e.getMessage());
        e.printStackTrace();
    }

    public ExportException(String defaultMessage) {
        super(defaultMessage);
    }
}
