package com.hb0730.jpa.specification.core.handler;

import com.hb0730.jpa.specification.annotation.Equals;
import com.hb0730.jpa.specification.core.AbstractPredicateHandler;
import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/27
 */
@Slf4j
public class EqualsPredicateHandler extends AbstractPredicateHandler {
    @Override
    public Class<? extends Annotation> getAnnotation() {
        return Equals.class;
    }

    @Override
    public <Z, X> Predicate buildPredicate(CriteriaBuilder criteriaBuilder, From<Z, X> from, String fieldName, @Nullable Object value, @Nullable Annotation annotation) {
        if (null != annotation) {
            Equals equals = (Equals) annotation;
            // 是否允许查询null
            if (value == null && equals.allowNull()) {
                return criteriaBuilder.and(super.buildIsNullPredicate(criteriaBuilder, from, fieldName));
            }
        }
        if (null == value) {
            return null;
        }
        return criteriaBuilder.and(super.buildEqualsPredicate(criteriaBuilder, from, fieldName, value));
    }
}
