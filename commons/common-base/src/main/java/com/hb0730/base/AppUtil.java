package com.hb0730.base;

import cn.hutool.extra.spring.EnableSpringUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.google.common.collect.Maps;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * AppUtil
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
@EnableSpringUtil
public class AppUtil extends SpringUtil {
    /**
     * 根据注解获取bean
     *
     * @param annotationType 注解类型
     * @param type           bean类型
     * @param <T>            bean类型
     * @return bean
     */
    public static <T> Map<String, T> getBeansWithAnnotation(Class<? extends Annotation> annotationType,
                                                            Class<T> type) {

        return Maps.transformEntries(
                SpringUtil.getApplicationContext().getBeansWithAnnotation(annotationType),
                (key, value) -> type.cast(value)
        );
    }

    /**
     * 获取bean
     *
     * @param type         bean类型
     * @param defaultValue 默认值
     * @param <T>          bean类型
     * @return bean
     */
    public static <T> T getBean(Class<T> type, T defaultValue) {
        T value = SpringUtil.getBean(type);
        return value == null ? defaultValue : value;
    }
}