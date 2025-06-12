package com.hb0730.mybatis.query.core.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.mybatis.query.annotation.Between;
import com.hb0730.mybatis.query.core.AbstractQueryHandler;
import jakarta.annotation.Nonnull;

import java.lang.annotation.Annotation;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/13
 */
public class BetweenQueryHandler extends AbstractQueryHandler {

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return Between.class;
    }

    @Override
    public void buildQuery(QueryWrapper<?> queryWrapper, String column, @Nonnull Object value) {
        super.buildBetween(queryWrapper, column, value);
    }
}
