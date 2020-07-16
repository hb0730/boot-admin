package com.hb0730.boot.admin.commons.utils.validator;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;

import javax.validation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 校验
 *
 * @author bing_huang
 * @date 2020/07/16 8:59
 * @since V1.0
 */
public class ValidatorUtils {
    private static volatile Validator VALIDATOR;

    private ValidatorUtils() {

    }

    /**
     * 获取validator
     *
     * @return Validator
     */
    public static Validator getValidator() {
        if (VALIDATOR == null) {
            synchronized (ValidatorUtils.class) {
                if (VALIDATOR == null) {
                    VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
                }
            }
        }
        return VALIDATOR;
    }

    /**
     * 校验
     *
     * @param obj   需校验的bean
     * @param group 分组
     * @throws ConstraintViolationException
     */
    public static void validate(Object obj, Class<?>... group) {
        Validator validator = getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj, group);
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    /**
     * 校验
     *
     * @param objs   iterable
     * @param groups groups
     * @throws ConstraintViolationException
     */
    public static void validate(@Nullable Iterable<?> objs, @Nullable Class<?>... groups) {
        if (objs == null) {
            return;
        }
        Validator validator = getValidator();

        // wrap index
        AtomicInteger i = new AtomicInteger(0);
        final Set<ConstraintViolation<?>> allViolations = new LinkedHashSet<>();
        objs.forEach(obj -> {
            int index = i.getAndIncrement();
            Set<? extends ConstraintViolation<?>> violations = validator.validate(obj, groups);
            violations.forEach(violation -> {
                Path path = violation.getPropertyPath();
                if (path instanceof PathImpl) {
                    PathImpl pathImpl = (PathImpl) path;
                    pathImpl.makeLeafNodeIterableAndSetIndex(index);
                }
                allViolations.add(violation);
            });
        });
        if (!CollectionUtils.isEmpty(allViolations)) {
            throw new ConstraintViolationException(allViolations);
        }
    }

    /**
     * 将字段验证错误转换为标准的map型，key:value = field:message
     *
     * @param constraintViolations constraint violations(contain error information)
     * @return error detail map
     */
    @NonNull
    public static Map<String, String> mapWithValidError(Set<ConstraintViolation<?>> constraintViolations) {
        if (CollectionUtils.isEmpty(constraintViolations)) {
            return Collections.emptyMap();
        }

        Map<String, String> errMap = new HashMap<>(4);
        // Format the error message
        constraintViolations.forEach(
                constraintViolation ->
                        errMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage()));
        return errMap;
    }

    /**
     * 将字段验证错误转换为标准的map型，key:value = field:message
     *
     * @param fieldErrors 字段错误组
     * @return 如果返回null，则表示未出现错误
     */
    public static Map<String, String> mapWithFieldError(@Nullable List<FieldError> fieldErrors) {
        if (CollectionUtils.isEmpty(fieldErrors)) {
            return Collections.emptyMap();
        }

        Map<String, String> errMap = new HashMap<>(4);
        fieldErrors.forEach(filedError -> errMap.put(filedError.getField(), filedError.getDefaultMessage()));
        return errMap;
    }

}
