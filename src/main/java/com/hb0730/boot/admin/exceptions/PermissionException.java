package com.hb0730.boot.admin.exceptions;

import com.hb0730.boot.admin.commons.enums.ResponseStatusEnum;
import lombok.Getter;
import lombok.NonNull;

/**
 * 无权限异常
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class PermissionException extends AbstractException {
    @Getter
    private ResponseStatusEnum status;
    @Getter
    private String data;

    public PermissionException() {
    }

    public PermissionException(@NonNull ResponseStatusEnum status, String message) {
        super(message);
        this.status = status;
        this.data = message;
    }
}
