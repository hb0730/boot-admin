package com.hb0730.boot.admin.annotation;

import com.hb0730.boot.admin.aspectj.FieldInfoAspectj;

import java.lang.annotation.*;

/**
 * 属性描述
 *
 * @author bing_huang
 * @see FieldClass
 * @see FieldInfoAspectj
 * @since 3.0.0
 */
@Documented
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface FieldInfo {
    /**
     * 属性名称
     *
     * @return 属性名称
     */
    String name() default "";

    /**
     * 表字段
     *
     * @return 表字段
     */
    String columnName() default "";
}
