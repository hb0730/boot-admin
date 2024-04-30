package com.hb0730.query.mybatis.plus.core.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.query.annotation.Equals;
import com.hb0730.query.mybatis.plus.core.AbstractQueryHandler;

import java.lang.annotation.Annotation;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
public class EqualsQueryHandler extends AbstractQueryHandler {

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return Equals.class;
    }

    @Override
    public void buildQuery(QueryWrapper<?> queryWrapper, String column, Object value, Annotation annotation) {
        if (null != annotation) {
            Equals equals = (Equals) annotation;
            if (null == value && equals.allowNull()) {
                queryWrapper.isNull(column);
                return;
            } else if (null == value) {
                return;
            }
        }
        queryWrapper.eq(column, value);
    }
}
