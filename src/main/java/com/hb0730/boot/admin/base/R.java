package com.hb0730.boot.admin.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 接口返回数据格式
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/11
 */
@Data
@EqualsAndHashCode
public class R<T> implements Serializable {
    public static final Integer OK_200 = 200;
    public static final Integer SERVER_ERROR_500 = 500;
    /**
     * 默认失败 状态码
     */
    public static final Integer SUC_0 = 0;
    /**
     * 成功状态码
     */
    public static final Integer SUC_1 = 1;

    /**
     * 成功标志
     */
    @Schema(description = "成功标识")
    private boolean success = true;
    /**
     * 返回处理消息
     */
    @Schema(description = "返回处理消息")
    private String message = "操作成功!";
    /**
     * 返回代码
     */
    @Schema(description = "返回代码")
    private Integer code = 0;
    /**
     * 返回数据对象 data
     */
    @Schema(description = "返回数据对象")
    private T result;
    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    private long timestamp = System.currentTimeMillis();

    public R() {
    }

    public R(T result) {
        this.code = SUC_1;
        this.result = result;
    }

    public R(boolean success, String message) {
        this.success = success;
        this.message = message;
        if (success) {
            this.code = SUC_1;
        } else {
            this.code = SUC_0;
        }
    }

    /**
     * 接口返回数据:成功
     *
     * @param message 消息
     * @return 接口返回数据:成功
     */
    public R<T> success(String message) {
        this.message = message;
        this.code = OK_200;
        this.success = true;
        return this;
    }

    /**
     * 接口返回数据:失败，code: 500
     *
     * @param message 消息
     * @return 接口返回数据:失败
     */
    public R<T> error(String message) {
        return error(SERVER_ERROR_500, message);
    }

    /**
     * 接口返回数据:失败
     *
     * @param code    代码
     * @param message 消息
     * @param <T>     .
     * @return 接口返回数据:失败
     */
    public static <T> R<T> error(int code, String message) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMessage(message);
        r.setSuccess(false);
        return r;
    }

    /**
     * 接口返回数据:成功
     *
     * @param <T> .
     * @return 接口返回数据:成功
     */
    public static <T> R<T> OK() {
        return OK("成功！", null);
    }

    /**
     * 接口返回数据:成功
     *
     * @param data 数据
     * @param <T>  .
     * @return 接口返回数据:成功
     */
    public static <T> R<T> OK(T data) {
        return OK("成功", data);
    }

    /**
     * 接口返回数据:成功
     *
     * @param code    代码
     * @param message 消息
     * @param <T>     .
     * @return 接口返回数据:成功
     */
    public static <T> R<T> OK(int code, String message) {
        return OK(code, message, null);
    }

    /**
     * 接口返回数据:成功
     *
     * @param message 消息
     * @param <T>     .
     * @return 接口返回数据:成功
     */
    public static <T> R<T> OK(String message) {
        return OK(OK_200, message, null);
    }

    /**
     * 接口返回数据:成功
     *
     * @param message 消息
     * @param data    数据
     * @param <T>     .
     * @return 接口返回数据:成功
     */
    public static <T> R<T> OK(String message, T data) {
        return OK(OK_200, message, data);
    }

    /**
     * 接口返回数据:成功
     *
     * @param code    代码
     * @param message 消息
     * @param data    数据
     * @param <T>     .
     * @return 接口返回数据:成功
     */
    public static <T> R<T> OK(int code, String message, T data) {
        R<T> r = new R<>();
        r.setSuccess(true);
        r.setCode(code);
        r.setMessage(message);
        r.setResult(data);
        return r;
    }

    /**
     * 接口返回数据:失败
     *
     * @param message 消息
     * @param <T>     .
     * @return 接口返回数据:失败
     */
    public static <T> R<T> NG(String message) {
        return NG(message, null);
    }

    /**
     * 接口返回数据:失败
     *
     * @param message 消息
     * @param data    数据
     * @param <T>     .
     * @return 接口返回数据:失败
     */
    public static <T> R<T> NG(String message, T data) {
        return NG(SERVER_ERROR_500, message, data);
    }


    /**
     * 接口返回数据:失败
     *
     * @param code    代码
     * @param message 消息
     * @param <T>     .
     * @return 接口返回数据:失败
     */
    public static <T> R<T> NG(int code, String message) {
        return NG(code, message, null);
    }

    /**
     * 接口返回数据:失败
     *
     * @param code    代码
     * @param message 消息
     * @param data    数据
     * @param <T>     .
     * @return 接口返回数据:失败
     */
    public static <T> R<T> NG(int code, String message, T data) {
        R<T> r = new R<>();
        r.setSuccess(false);
        r.setCode(code);
        r.setMessage(message);
        r.setResult(data);
        return r;
    }
}
