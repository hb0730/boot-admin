package com.hb0730.boot.admin.exceptions;

/**
 * json 异常
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/1/10
 * @since 3.1.0
 */
public class JsonException extends RuntimeException {
    public JsonException(Throwable cause) {
        super(cause);
    }
}
