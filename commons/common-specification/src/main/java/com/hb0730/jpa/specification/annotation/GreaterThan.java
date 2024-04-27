package com.hb0730.jpa.specification.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于“大于”({@code >})场景的注解.
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/27
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GreaterThan {

    /**
     * 注解的实体字段属性名称，默认为空或空字符串时将使用属性名称.
     *
     * @return 值
     */
    String value() default "";

}
