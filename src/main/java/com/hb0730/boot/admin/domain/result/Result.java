package com.hb0730.boot.admin.domain.result;

import java.io.Serializable;

/**
 * 响应
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class Result<T> implements Serializable {
    /**
     * 信息
     */
    private String message;
    /**
     * 状态code
     */
    private String code;
    /**
     * 结果信息
     */
    private T data;

    public Result(String code, String message, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
