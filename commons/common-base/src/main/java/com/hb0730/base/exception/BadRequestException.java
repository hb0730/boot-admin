package com.hb0730.base.exception;

import lombok.Getter;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
@Getter
public class BadRequestException extends RuntimeException {
    private Integer code;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}