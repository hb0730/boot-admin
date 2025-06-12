package com.hb0730.mybatis.query.core.impl;

import com.hb0730.mybatis.query.annotation.In;
import com.hb0730.mybatis.query.core.AbstractQueryHandler;
import jakarta.annotation.Nonnull;

import java.lang.annotation.Annotation;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/8
 */
public class InQueryHandler extends AbstractQueryHandler {

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return In.class;
    }

    @Override
    public void buildQuery(com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<?> queryWrapper, String column
            , @Nonnull Object value) {
        queryWrapper.in(column, value);
    }
}
