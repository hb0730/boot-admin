package com.hb0730.base.jpa.core.id;

import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.annotations.ValueGenerationType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@IdGeneratorType(DefaultIdentifierGenerator.class)
@ValueGenerationType(generatedBy = DefaultIdentifierGenerator.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface IdGenerator {
}
