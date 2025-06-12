package com.hb0730.mybatis.query.annotation;

import java.lang.annotation.Documented;

/**
 * 用于“IN”({@code IN})场景的注解.
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/8
 */
@Documented
@java.lang.annotation.Target({java.lang.annotation.ElementType.FIELD})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface In {

    /**
     * 注解的实体字段属性名称，默认为空或空字符串时将使用属性名称.
     *
     * @return 值
     */
    String value() default "";

    /**
     * 是否使用驼峰命名，默认为 {@code true}.
     *
     * @return 值
     */
    boolean underCamel() default true;

    /**
     * 是否添加{@code  is_}前缀，只针对Boolean类型有效，默认为 {@code false}.
     */
    boolean isPrefix() default false;

}
