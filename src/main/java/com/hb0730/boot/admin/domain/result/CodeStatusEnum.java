package com.hb0730.boot.admin.domain.result;

/**
 * 响应状态
 *
 * @author bing_huang
 * @since 3.0.0
 */
public enum CodeStatusEnum {
    /**
     * 请求成功
     */
    SUCCESS("00000", "请求成功"),
    /**
     * 请求必填参数为空
     */
    PARAMS_REQUIRED_IS_NULL("A0410", "请求必填参数为空"),
    /**
     * 系统错误
     */
    FAIL("C0001", "系统执行错误"),

    ;

    private String code;
    private String message;

    CodeStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
