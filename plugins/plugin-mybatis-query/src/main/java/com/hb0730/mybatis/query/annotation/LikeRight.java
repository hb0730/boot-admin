package com.hb0730.mybatis.query.annotation;

/**
 * 用于“右模糊匹配某个元素”({@code LIKE 'value%'})场景的注解.
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/5
 */
public @interface LikeRight {

    /**
     * 注解的实体字段属性名称/字段column名称，默认为空或空字符串时将使用属性名称.
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
