package com.hb0730.jpa.specification.core.handler;

import com.hb0730.jpa.specification.annotation.Like;
import com.hb0730.jpa.specification.core.AbstractPredicateHandler;
import jakarta.annotation.Nonnull;
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
public class LikePredicateHandler extends AbstractPredicateHandler {
    @Override
    public Class<? extends Annotation> getAnnotation() {
        return Like.class;
    }

    @Override
    public <Z, X> Predicate _buildPredicate(CriteriaBuilder criteriaBuilder, From<Z, X> from, String fieldName, @Nonnull Object value) {
        return criteriaBuilder.and(
                super.buildLikePredicate(criteriaBuilder, from, fieldName, value)
        );
    }
}
