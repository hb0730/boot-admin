package com.hb0730.desensitize.core.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hb0730.desensitize.core.enums.DesensitizationType;
import com.hb0730.desensitize.core.serializer.AnnotationDesensitizeSerializer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对象脱敏注解
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/10
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = AnnotationDesensitizeSerializer.class)
@Documented
public @interface Desensitize {

    /**
     * 脱敏处理类
     *
     * @return Desensitization
     * @see com.hb0730.desensitize.core.enums.DesensitizationType
     * @see com.hb0730.desensitize.core.desensitization.DesensitizationFactory
     */
    String value() default "";

    /**
     * 脱敏处理类
     *
     * @return DesensitizationType
     * @see com.hb0730.desensitize.core.enums.DesensitizationType
     * @see com.hb0730.desensitize.core.desensitization.DesensitizationFactory
     */
    DesensitizationType type() default DesensitizationType.NONE;

    ;
}
