package com.hb0730.boot.admin.annotation;

import com.hb0730.boot.admin.commons.enums.BusinessTypeEnum;

import java.lang.annotation.*;

/**
 * 日记记录
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Log {
    /**
     * 是否启用 操作日志
     *
     * @return 是否启用
     */
    boolean enabled() default true;

    /**
     * 描述
     *
     * @return {String}
     */
    String value() default "";

    /**
     * 需要拦截的参数名称
     *
     * @return 参数名称
     */
    String[] paramsName() default {};


    /**
     * 业务参数类型
     *
     * @return {@link BusinessTypeEnum}
     */
    BusinessTypeEnum businessType() default BusinessTypeEnum.OTHER;

    /**
     * 是否拼接Controller类上的描述值
     *
     * @return 是否拼接
     */
    boolean controllerApiValue() default true;

    /**
     * 记录执行参数
     *
     * @return 是否记录
     */
    boolean request() default true;

    /**
     * 当 request = false时， 方法报错记录请求参数
     *
     * @return 是否记录
     */
    boolean requestByError() default true;

    /**
     * 记录返回参数
     *
     * @return 是否记录
     */
    boolean response() default true;
}
