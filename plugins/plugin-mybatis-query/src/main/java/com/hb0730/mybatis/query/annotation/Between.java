package com.hb0730.mybatis.query.annotation;

/**
 * 用于“区间匹配”({@code AND field NOT BETWEEN ... AND ...})场景的注解.
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/13
 */
@java.lang.annotation.Documented
@java.lang.annotation.Target(java.lang.annotation.ElementType.FIELD)
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Between {
    /**
     * 注解的实体字段属性名称，默认为空或空字符串时将使用属性名称.
     *
     * @return 字符串值
     */
    java.lang.String value() default "";

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
