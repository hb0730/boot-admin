package com.hb0730.boot.admin.commons.enums;

/**
 * 响应状态
 *
 * @author bing_huang
 * @since 3.0.0
 */
public enum ResponseStatusEnum implements ValueEnum<String> {
    /**
     * 请求成功
     */
    SUCCESS("00000", "请求成功"),
    // A类 用户端错误
    /**
     * 密码校验错误
     */
    USER_PASSWORD_V_FAIL("A0120","密码校验失败"),
    /**
     * 用户登录异常
     */
    USE_LOGIN_ERROR("A0200", "用户登录异常"),
    /**
     * 用户账号未找到
     */
    USER_NAME_NOT_FONT("A0201", "用户账户不存在"),
    /**
     * 用户密码错误
     */
    USER_PASSWORD_ERROR("A0210", "用户密码错误"),
    /**
     * 请求必填参数为空
     */
    PARAMS_REQUIRED_IS_NULL("A0410", "请求必填参数为空"),

    /**
     * 访问未授权
     */
    UNAUTHORIZED("A0301", "访问未授权"),
    /**
     * 系统错误
     */
    FAIL("C0001", "系统执行错误"),

    ;

    private String code;
    private String message;

    ResponseStatusEnum(String code, String message) {
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

    @Override
    public String getValue() {
        return this.code;
    }
}
