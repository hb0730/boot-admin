package com.hb0730.boot.admin.annotation;

import java.lang.annotation.*;

/**
 * 权限注解,用于拼接
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PreAuth {
    String value() default "";
}
