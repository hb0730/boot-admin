package com.hb0730.jpa.specification.core;

import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/27
 */
@FunctionalInterface
public interface PredicateHandler {
    /**
     * 根据字段、值等参数来构造 {@link Predicate} 条件实例.
     *
     * @param criteriaBuilder {@link CriteriaBuilder} 实例
     * @param from            {@link From} 实例
     * @param fieldName       属性字段名称
     * @param value           属性条件对应的值
     * @return {@link Predicate} 实例
     */
    Predicate buildPredicate(CriteriaBuilder criteriaBuilder, From<?, ?> from, String fieldName, @Nullable Object value);
}
