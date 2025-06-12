package com.hb0730.mybatis.query.core.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.mybatis.query.annotation.OrLike;
import com.hb0730.mybatis.query.core.AbstractQueryHandler;
import jakarta.annotation.Nonnull;

import java.lang.annotation.Annotation;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/1/8
 */
public class OrLikePredicateHandler extends AbstractQueryHandler {


    @Override
    public Class<? extends Annotation> getAnnotation() {
        return OrLike.class;
    }


    @Override
    public void buildQuery(QueryWrapper<?> queryWrapper, String column, @Nonnull Object value) {
        queryWrapper.or().like(column, value);
    }
}
