package com.hb0730.boot.admin.annotation;

import java.lang.annotation.*;

/**
 * 类描述
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ClassDescribe {
    String value() default "";
}
