package com.hb0730.boot.admin.domain.result;

/**
 * result工具类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class Results {
    /**
     * 请求成功
     *
     * @param data 响应数据
     * @param <T>  数据类型
     * @return {@link Result<T>} 返回成功数据
     */
    public static <T> Result<T> resultSuccess(T data) {
        return result(CodeStatusEnum.SUCCESS, data);
    }

    /**
     * 系统错误
     *
     * @param data 错误详情
     * @param <T>  错误类型
     * @return {@link Result<T>}
     */
    public static <T> Result<T> resultFail(T data) {
        return result(CodeStatusEnum.FAIL, data);
    }

    /**
     * 设置响应
     *
     * @param status 响应状态
     * @param data   要响应的数据
     * @param <T>    数据类型
     * @return {@link Result<T>}
     */
    public static <T> Result<T> result(CodeStatusEnum status, T data) {
        return result(status.getCode(), status.getMessage(), data);
    }

    /**
     * 设置响应
     *
     * @param code    响应状态
     * @param message 响应信息
     * @param data    要响应的数据
     * @param <T>     数据类型
     * @return {@link Result<T>}
     */
    public static <T> Result<T> result(String code, String message, T data) {
        return new Result<>(code, message, data);
    }
}
