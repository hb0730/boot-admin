package com.hb0730.excel.annotation;

import java.lang.annotation.RetentionPolicy;

/**
 * excel字段
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/10
 */
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
public @interface ExcelFields {

    /**
     * 字段
     *
     * @return .
     */
    ExcelField[] value() default {};
}