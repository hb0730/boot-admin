package com.hb0730.base.exception;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/27
 */
public class SpecificationException extends RuntimeException {

    public SpecificationException() {
        super();
    }

    public SpecificationException(String message) {
        super(message);
    }

    public SpecificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
