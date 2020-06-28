package com.hb0730.boot.admin.exception.export;

import com.hb0730.boot.admin.exception.BaseException;

/**
 * 导出异常
 *
 * @author bing_huang
 * @since V1.0
 */
public class ExportException extends BaseException {

    public ExportException(String message) {
        super(message);
    }

    public ExportException(String message, Throwable e) {
        super(message, e);
    }
}
