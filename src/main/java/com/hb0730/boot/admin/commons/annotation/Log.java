package com.hb0730.boot.admin.commons.annotation;

import com.hb0730.boot.admin.commons.constant.enums.BusinessTypeEnum;

import java.lang.annotation.*;

/**
 * <p>
 * 自定义日志记录
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {


    /**
     * 拦截参数
     */
    String[] paramsName() default {};

    /**
     * 模块
     */
    String module() default "";

    /**
     * 标题
     */
    String title() default "";

    /**
     * 功能
     */
    BusinessTypeEnum businessType() default BusinessTypeEnum.OTHER;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;
}
