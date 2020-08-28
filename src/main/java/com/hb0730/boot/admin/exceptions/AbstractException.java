package com.hb0730.boot.admin.exceptions;

import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import lombok.Getter;
import lombok.NonNull;

/**
 * @author bing_huang
 */
public abstract class AbstractException extends BusinessException {
    @Getter
    private ResponseStatusEnum status;
    @Getter
    private String data;

    public AbstractException() {
    }

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractException(@NonNull ResponseStatusEnum status, String message) {
        super(message);
        this.status = status;
        this.data = message;
    }
}
