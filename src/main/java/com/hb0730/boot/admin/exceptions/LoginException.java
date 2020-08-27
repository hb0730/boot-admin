package com.hb0730.boot.admin.exceptions;

import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import com.hb0730.boot.admin.commons.enums.ValueEnum;
import lombok.Getter;
import lombok.NonNull;

/**
 * 登录异常
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class LoginException extends BusinessException {
    @Getter
    private ResponseStatusEnum status;
    @Getter
    private String data;

    public LoginException() {
    }

    public LoginException(@NonNull ResponseStatusEnum status, String message) {
        super(message);
        this.status = status;
        this.data = message;
    }

    public LoginException(@NonNull String code, String message) {
        super(message);
        this.status = ValueEnum.valueToEnum(ResponseStatusEnum.class, code);
        this.data = message;
    }

    public LoginException(@NonNull ResponseStatusEnum status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.data = message;
    }

    public LoginException(@NonNull String code, String message, Throwable cause) {
        super(message, cause);
        this.status = ValueEnum.valueToEnum(ResponseStatusEnum.class, code);
        this.data = message;
    }
}
