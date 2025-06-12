package com.hb0730.mybatis.query.core;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.base.exception.BasicException;
import com.hb0730.base.utils.StrUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/5
 */
public abstract class AbstractQueryHandler {
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

    /**
     * 创建条件 <p>
     * 若结束值为空，则退化生成为大于等于的条件，若开始值为空.则退化生成为小于等于的条件，若开始值或结束值都为空，则直接抛出异常.
     *
     * @param queryWrapper queryWrapper
     * @param column       字段
     * @param value        值
     */
    protected void buildBetween(QueryWrapper<?> queryWrapper, String column, Object value) {
        if (value.getClass().isArray()) {
            Object[] arr = (Object[]) value;
            this.buildBetween(queryWrapper, column, arr[0], arr.length == 2 ? arr[1] : null);
        } else if (value instanceof List<?> list) {

            this.buildBetween(queryWrapper, column, list.get(0), list.size() == 2 ? list.get(1) : null);
        } else {
            throw new BasicException("构建【@Between】注解区间查询时，传入的参数类型不支持！");
        }
    }

    private void buildBetween(QueryWrapper<?> queryWrapper, String column, Object starValue, Object endValue) {
        if (!isEmpty(starValue) && !isEmpty(endValue)) {
            queryWrapper.between(column, starValue, endValue);
        } else if (!isEmpty(starValue)) {
            queryWrapper.ge(column, starValue);
        } else if (!isEmpty(endValue)) {
            queryWrapper.le(column, endValue);
        } else {
            throw new BasicException("构建【@Between】注解区间查询时，开始和结束的区间值均为【null】，无法构建区间查询条件！");
        }
    }


    private boolean isEmpty(Object value) {
        return Objects.isNull(value) || StrUtil.isBlank(Objects.toString(value));
    }
}
