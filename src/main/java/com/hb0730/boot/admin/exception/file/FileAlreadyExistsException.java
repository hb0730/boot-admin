package com.hb0730.boot.admin.exception.file;

import com.hb0730.boot.admin.exception.BaseException;

/**
 * 文件存在异常
 *
 * @author bing_huang
 * @date 2020/06/30 9:05
 * @since V1.0
 */
public class FileAlreadyExistsException extends BaseException {
    public FileAlreadyExistsException(String message) {
        super(message);
    }

    public FileAlreadyExistsException(String message, Object... args) {
        super(message, args);
    }
}
