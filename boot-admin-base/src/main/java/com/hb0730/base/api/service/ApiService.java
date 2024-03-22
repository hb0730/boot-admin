package com.hb0730.base.api.service;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对外接口实现类注解
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/1/23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
@Component
public @interface ApiService {
    /**
     * 接口名称
     */
    @AliasFor("name")
    String value() default "XX";

    /**
     * 接口名称
     */
    @AliasFor("value")
    String name() default "XX";

    /**
     * 接口分组
     */
    String group() default "";

    /**
     * 接口渠道
     */
    String channel() default "";

    /**
     * 接口说明
     */
    String desc() default "";

}
