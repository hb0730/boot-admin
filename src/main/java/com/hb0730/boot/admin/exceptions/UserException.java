package com.hb0730.boot.admin.exceptions;

import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import com.hb0730.boot.admin.commons.enums.ValueEnum;
import lombok.Getter;
import lombok.NonNull;

/**
 * 用户相关异常
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class UserException extends AbstractException {
    @Getter
    private final ResponseStatusEnum status;
    @Getter
    private final String data;

    public UserException(@NonNull ResponseStatusEnum status, String message) {
        super(status, message);
        this.status = status;
        this.data = message;
    }

    public UserException(@NonNull String code, String message) {
        super(message);
        this.status = ValueEnum.valueToEnum(ResponseStatusEnum.class, code);
        this.data = message;
    }

    public UserException(@NonNull ResponseStatusEnum status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.data = message;
    }

    public UserException(@NonNull String code, String message, Throwable cause) {
        super(message, cause);
        this.status = ValueEnum.valueToEnum(ResponseStatusEnum.class, code);
        this.data = message;
    }
}
