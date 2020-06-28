package com.hb0730.boot.admin.exception.file;

import com.hb0730.boot.admin.exception.BaseException;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class FileOperationException extends BaseException {
    public FileOperationException(String message) {
        super(message);
    }

    public FileOperationException(String message, Object... args) {
        super(message, args);
    }
}
