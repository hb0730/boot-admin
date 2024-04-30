package com.hb0730.common.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Setter
@Getter
public class JR<T> implements Serializable {
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
    private boolean success = true;

    /**
     * 状态码
     */
    private int code = 1;

    /**
     * 返回处理消息
     */
    private String message = "操作成功！";

    /**
     * 返回数据对象 data
     */
    private T result;

    /**
     * 接口返回数据
     */
    public JR() {
    }

    /***/
    public JR(T data) {
        this.code = SUC_1;
        this.result = data;
    }

    /***/
    public JR(boolean success, String message) {
        this.success = success;
        this.message = message;
        if (success) {
            this.code = SUC_1;
        } else {
            this.code = SUC_0;
        }
    }

    /***/
    public JR(boolean success, int code, String message) {
        this.success = success;
        this.message = message;
        this.code = code;
    }


    /**
     * 接口返回数据:成功
     *
     * @param message 消息
     * @return 处理
     */
    public JR<T> success(String message) {
        this.message = message;
        this.success = true;
        this.code = SUC_1;
        return this;
    }

    /**
     * 接口返回数据:成功
     *
     * @param data    返回数据对象
     * @param message 消息
     * @return 处理
     */
    public JR<T> success(T data, String message) {
        this.message = message;
        this.success = true;
        this.code = SUC_1;
        this.result = data;
        return this;
    }

    /**
     * 接口返回数据:失败
     *
     * @param message 错误消息
     * @return 处理失败
     */
    public JR<T> error(String message) {
        this.message = message;
        this.success = false;
        this.code = SUC_0;
        return this;
    }

    /**
     * 接口返回数据:失败
     *
     * @param code    状态码
     * @param message 错误消息
     * @return 处理失败
     */
    public JR<T> error(int code, String message) {
        this.message = message;
        this.success = false;
        this.code = code;
        return this;
    }

    /**
     * 接口返回数据:成功
     */
    public static <T> JR<T> ok() {
        JR<T> r = new JR<>();
        r.setSuccess(true);
        r.setCode(SUC_1);
        return r;
    }

    /**
     * 接口返回数据:成功
     *
     * @param msg 消息
     * @return 接口返回数据:成功
     */
    public static <T> JR<T> ok(String msg) {
        JR<T> r = new JR<>();
        r.setSuccess(true);
        r.setCode(SUC_1);
        r.setMessage(msg);
        return r;
    }

    /**
     * 接口返回数据:成功
     *
     * @param data 数据
     * @return 接口返回数据:成功
     */
    public static <T> JR<T> okData(T data) {
        JR<T> r = new JR<>();
        r.setSuccess(true);
        r.setCode(SUC_1);
        r.setResult(data);
        return r;
    }


    /**
     * 接口返回数据:失败
     *
     * @param msg 消息
     * @return 接口返回数据:失败
     */
    public static <T> JR<T> fail(String msg) {
        return fail(SUC_0, msg);
    }

    /**
     * 接口返回数据:失败
     *
     * @param code 代码
     * @param msg  消息
     * @return 接口返回数据:失败
     */
    public static <T> JR<T> fail(Integer code, String msg) {
        JR<T> r = new JR<>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    /**
     * 接口返回数据:响应失败并返回数据
     *
     * @param data 数据
     * @return 接口返回数据:响应失败并返回数据
     */
    public static <T> JR<T> failData(T data, String msg) {
        JR<T> r = new JR<>();
        r.setSuccess(false);
        r.setCode(SUC_0);
        r.setResult(data);
        r.setMessage(msg);
        return r;
    }
}