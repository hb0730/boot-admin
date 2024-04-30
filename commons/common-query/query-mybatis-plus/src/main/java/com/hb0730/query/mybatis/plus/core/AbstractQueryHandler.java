package com.hb0730.query.mybatis.plus.core;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.lang.annotation.Annotation;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
public abstract class AbstractQueryHandler implements QueryHandler {

    /**
     * 获取注解
     *
     * @return 注解
     */
    public abstract Class<? extends Annotation> getAnnotation();

    /**
     * 创建条件
     *
     * @param queryWrapper queryWrapper
     * @param column       字段
     * @param value        值
     */
    public void buildQuery(QueryWrapper<?> queryWrapper, String column, @Nullable Object value,
                           Annotation annotation) {
        if (null == value) {
            return;
        }
        buildQuery(queryWrapper, column, value);
    }

    /**
     * 创建条件
     *
     * @param queryWrapper queryWrapper
     * @param column       字段
     * @param value        值
     */
    public void buildQuery(QueryWrapper<?> queryWrapper, String column, @Nonnull Object value) {
        buildQuery(queryWrapper, column, value, null);
    }
}
