package com.hb0730.boot.admin.domain.result;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 响应
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class Result<T> implements Serializable {
    /**
     * <p>
     * 状态码
     * </p>
     */
    private String code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回的信息或者详情
     */
    private T data;

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result<?> result = (Result<?>) o;
        return Objects.equals(code, result.code) &&
                Objects.equals(message, result.message) &&
                Objects.equals(data, result.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, data);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
