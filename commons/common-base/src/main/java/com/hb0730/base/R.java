package com.hb0730.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 接口返回数据格式
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
@Data
public class R<T> implements Serializable {
    /**
     * {@code 200 OK} (HTTP/1.0 - RFC 1945)
     */
    public static final Integer SC_OK_200 = 200;
    /**
     * {@code 500 Server Error} (HTTP/1.0 - RFC 1945)
     */
    public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /**
     * 访问权限认证未通过 510
     */
    public static final Integer SC_JEECG_NO_AUTHZ = 510;
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
    @Schema(description = "成功标志")
    private boolean success = true;
    /**
     * 返回处理消息
     */
    @Schema(description = "返回处理消息")
    private String message = "操作成功！";
    /**
     * 返回代码
     */
    @Schema(description = "返回代码")
    private Integer code = 0;
    /**
     * 返回数据对象 data
     */
    @Schema(description = "返回数据对象")
    private T data;
    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    private long timestamp = System.currentTimeMillis();


    /**
     * 接口返回数据
     */
    public R() {
    }

    /***/
    public R(T data) {
        this.code = SUC_1;
        this.data = data;
    }

    /***/
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
        this.code = SC_OK_200;
        this.success = true;
        return this;
    }

    /**
     * 接口返回数据:失败
     *
     * @param msg 消息
     * @return 接口返回数据:失败
     */
    public R<T> error(String msg) {
        return error(SC_INTERNAL_SERVER_ERROR_500, msg);
    }

    /**
     * 接口返回数据:失败
     *
     * @param code 代码
     * @param msg  消息
     * @param <T>  数据类型
     * @return 接口返回数据:失败
     */
    public static <T> R<T> error(int code, String msg) {
        R<T> r = new R<T>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    /**
     * 接口返回数据:成功
     *
     * @param <T> 数据类型
     * @return 接口返回数据:成功
     */
    public static <T> R<T> OK() {
        return OK(SC_OK_200, "成功", null);
    }

    /**
     * 接口返回数据:成功
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return 接口返回数据:成功
     */
    public static <T> R<T> OK(T data) {
        return OK(SC_OK_200, "成功", data);
    }

    /**
     * 接口返回数据:成功
     *
     * @param msg  消息
     * @param data 数据
     * @param <T>  数据类型
     * @return 接口返回数据:成功
     */
    public static <T> R<T> OK(String msg, T data) {
        return OK(SC_OK_200, msg, data);
    }

    /**
     * 接口返回数据:成功
     *
     * @param code 代码
     * @param msg  消息
     * @param data 数据
     * @param <T>  数据类型
     * @return 接口返回数据:成功
     */
    public static <T> R<T> OK(int code, String msg, T data) {
        R<T> r = new R<>();
        r.setSuccess(true);
        r.setCode(code);
        r.setMessage(msg);
        r.setData(data);
        return r;
    }


    /**
     * 接口返回数据:失败
     *
     * @param msg 消息
     * @param <T> 数据类型
     * @return 接口返回数据:失败
     */
    public static <T> R<T> NG(String msg) {
        return NG(SC_INTERNAL_SERVER_ERROR_500, msg, null);
    }

    /**
     * 接口返回数据:失败
     *
     * @param msg  消息
     * @param data 数据
     * @param <T>  数据类型
     * @return 接口返回数据:失败
     */
    public static <T> R<T> NG(String msg, T data) {
        return NG(SC_INTERNAL_SERVER_ERROR_500, msg, data);
    }

    /**
     * 接口返回数据:失败
     *
     * @param code 代码
     * @param msg  消息
     * @param <T>  数据类型
     * @return 接口返回数据:失败
     */
    public static <T> R<T> NG(int code, String msg) {
        return NG(code, msg, null);
    }

    /**
     * 接口返回数据:失败
     *
     * @param code 代码
     * @param msg  消息
     * @param data 数据
     * @param <T>  数据类型
     * @return 接口返回数据:失败
     */
    public static <T> R<T> NG(int code, String msg, T data) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        r.setData(data);
        return r;
    }

}