package com.hb0730.mybatis.query.core.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hb0730.mybatis.query.annotation.IsNull;
import com.hb0730.mybatis.query.core.AbstractQueryHandler;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/5
 */
@Slf4j
public class IsNullQueryHandler extends AbstractQueryHandler {
    @Override
    public Class<? extends Annotation> getAnnotation() {
        return IsNull.class;
    }

    @Override
    public void buildQuery(QueryWrapper<?> queryWrapper, String column, @Nonnull Object value) {
        queryWrapper.isNull(column);
    }
}
