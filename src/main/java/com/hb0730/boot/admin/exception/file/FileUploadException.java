package com.hb0730.boot.admin.exception.file;

import com.hb0730.boot.admin.exception.BaseException;

/**
 * <p>
 * 文件上传错误
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class FileUploadException extends BaseException {
    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Object... args) {
        super(message, args);
    }

    public FileUploadException(String message, Throwable e, Object... args) {
        super(message, e, args);
    }
}
