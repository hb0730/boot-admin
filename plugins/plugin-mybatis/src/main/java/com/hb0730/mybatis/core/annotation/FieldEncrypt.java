package com.hb0730.mybatis.core.annotation;


import com.hb0730.mybatis.core.enums.Algorithm;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段加密
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/14
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.FIELD,
        ElementType.PARAMETER,
        ElementType.METHOD
})
@Inherited
public @interface FieldEncrypt {
    /**
     * 是否加密
     *
     * @return 是否加密
     */
    boolean value() default true;

    /**
     * 是否解密
     *
     * @return 是否解密
     */
    boolean decrypt() default true;

    /**
     * 加密算法
     *
     * @return {@link Algorithm}
     */
    Algorithm algorithm() default Algorithm.GLOBAL;


    /**
     * 加密key
     *
     * @return key
     */
    String key() default "";

    /**
     * 加密向量
     *
     * @return iv
     */
    String iv() default "";
}
