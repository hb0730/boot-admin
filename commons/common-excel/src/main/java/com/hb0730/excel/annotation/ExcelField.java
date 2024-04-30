package com.hb0730.excel.annotation;

import com.hb0730.excel.filedtype.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/9
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {
    /**
     * 属性名称
     * <p>
     * 如果根据属性名称获取不到值,则根据{@code getter}方法名称获取
     *
     * @return .
     */
    String attrName() default "";

    /**
     * 标题
     *
     * @return .
     */
    String title() default "";


    /**
     * 类型,默认全部
     *
     * @return .
     */
    Type type() default Type.ALL;

    /**
     * 对齐方式
     *
     * @return .
     */
    Align align() default Align.AUTO;

    /**
     * 字段类型
     *
     * @return .
     */
    Class<? extends FieldType> fieldType() default FieldType.class;

    /**
     * 列宽,默认 330,如果为0则隐藏
     *
     * @return .
     */
    int width() default -1;

    /**
     * 顺序
     *
     * @return .
     */
    int order() default 0;

    /**
     * 所在列
     *
     * @return .
     */
    int column() default -1;

    /**
     * 数据格式
     *
     * @return .
     */
    String dataFormat() default "";


    public enum Align {
        AUTO, LEFT, CENTER, RIGHT
    }

    enum Type {
        ALL,
        EXPORT,
        IMPORT
    }

}