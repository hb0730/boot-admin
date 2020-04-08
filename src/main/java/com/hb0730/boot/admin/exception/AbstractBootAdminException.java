package com.hb0730.boot.admin.exception;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public abstract class AbstractBootAdminException extends RuntimeException {
    /**
     * Error errorData.
     */
    private Object errorData;

    public AbstractBootAdminException(String message) {
        super(message);
    }

    public AbstractBootAdminException(String message, Throwable cause) {
        super(message, cause);
    }

    @NonNull
    public abstract String getStatus();

    @Nullable
    public Object getErrorData() {
        return errorData;
    }

    /**
     * Sets error errorData.
     *
     * @param errorData error data
     * @return current exception.
     */
    @NonNull
    public AbstractBootAdminException setErrorData(@Nullable Object errorData) {
        this.errorData = errorData;
        return this;
    }
}
