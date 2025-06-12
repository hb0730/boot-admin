package com.hb0730.operator.log.core.annotation;


import com.hb0730.operator.log.core.enums.ReturnType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作日志
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperatorLog {
    /**
     * 操作类型
     */
    String value();

    /**
     * 是否记录参数
     * <p>
     * - {@link org.springframework.web.bind.annotation.RequestBody}
     * - {@link org.springframework.web.bind.annotation.RequestParam}
     * - {@link org.springframework.web.bind.annotation.RequestHeader}
     * - {@link org.springframework.web.bind.annotation.PathVariable}
     * <p>
     * 使用 @IgnoreParameter 可以忽略参数记录 {@link IgnoreParameter}
     * 如果只需要忽略对象中的某个字段可以使用 {@code @Desensitize(toEmpty = true)} 标注
     */
    boolean parameter() default true;

    /**
     * 返回值处理
     */
    ReturnType ret() default ReturnType.JSON;
}
