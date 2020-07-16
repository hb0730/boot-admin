package com.hb0730.boot.admin.commons.domain.result;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class ResponseResult {
    private ResponseResult() {
    }

    /**
     * <p>
     * 成功响应
     * </p>
     *
     * @param data 响应数据
     * @param <T>  数据类型
     * @return 成功
     */
    public static <T> Result<T> resultSuccess(T data) {
        return result(CodeStatusEnum.SUCCESS, data);
    }

    /**
     * <p>
     * 失败响应
     * </p>
     *
     * @param data 响应数据
     * @param <T>  数据类型
     * @return 失败
     */
    public static <T> Result<T> resultFall(T data) {
        return result(CodeStatusEnum.FAIL, data);
    }

    /**
     * <p>
     * 返回同一格式
     * </p>
     *
     * @param codeStatusEnum 响应昨天
     * @param data           返回数据
     * @param <T>            数据类型
     * @return ResultJson
     */
    public static <T> Result<T> result(CodeStatusEnum codeStatusEnum, T data) {
        return new Result<T>(codeStatusEnum.getCode(), codeStatusEnum.getMessage(), data);
    }
}
