package com.hb0730.boot.admin.commons.web.response;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public enum CodeStatusEnum {
    /**
     * 请求成功
     */
    SUCCESS("BA20000", "请求成功"),
    /**
     * 请求失败
     */
    FAIL("BA20002", "请求失败"),

    /**
     * 用户不存在
     */
    USERNAME_NOTFOUND("BA20004", "用户不存在"),
    /**
     * 未授权
     */
    UNAUTHORIZED("BA20005", "未授权"),
    /**
     * 授权异常
     */
    FORBIDDEN("BA20006"," 授权异常")
    ;
    /**
     * 状态码
     */
    private String code;
    /**
     * 信息
     */
    private String message;

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

    CodeStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}

