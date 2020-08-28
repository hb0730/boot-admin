package com.hb0730.boot.admin.exceptions;

import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import lombok.Getter;
import lombok.NonNull;

/**
 * 用户账号信息
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class AccountException extends AbstractException {
    @Getter
    private final ResponseStatusEnum status;
    @Getter
    private final String data;

    public AccountException(@NonNull ResponseStatusEnum status, String message) {
        super(message);
        this.status = status;
        this.data = message;
    }
}
