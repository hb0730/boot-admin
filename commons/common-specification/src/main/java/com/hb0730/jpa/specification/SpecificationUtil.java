package com.hb0730.jpa.specification;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import com.hb0730.base.exception.SpecificationException;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.jpa.specification.annotation.Equals;
import com.hb0730.jpa.specification.annotation.GreaterThan;
import com.hb0730.jpa.specification.annotation.GreaterThanEqual;
import com.hb0730.jpa.specification.annotation.IsNull;
import com.hb0730.jpa.specification.annotation.LessThan;
import com.hb0730.jpa.specification.annotation.LessThanEqual;
import com.hb0730.jpa.specification.annotation.Like;
import com.hb0730.jpa.specification.annotation.Query;
import com.hb0730.jpa.specification.bean.Pair;
import com.hb0730.jpa.specification.core.AbstractPredicateHandler;
import com.hb0730.jpa.specification.core.handler.EqualsPredicateHandler;
import com.hb0730.jpa.specification.core.handler.GreaterThanEqualPredicateHandler;
import com.hb0730.jpa.specification.core.handler.GreaterThanPredicateHandler;
import com.hb0730.jpa.specification.core.handler.IsNullPredicateHandler;
import com.hb0730.jpa.specification.core.handler.LessThanEqualPredicateHandler;
import com.hb0730.jpa.specification.core.handler.LessThanPredicateHandler;
import com.hb0730.jpa.specification.core.handler.LikePredicateHandler;
import com.hb0730.jpa.specification.core.handler.QueryPredicateHandler;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Specification 工具类.
 * 参考自 <a href="https://github.com/blinkfox/fenix">fenix</a>
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/27
 */
public class SpecificationUtil {

    private static final Map<Class<?>, AbstractPredicateHandler> specificationHandlerMap = new HashMap<>();

    static {
        specificationHandlerMap.put(Equals.class, new EqualsPredicateHandler());
        specificationHandlerMap.put(Like.class, new LikePredicateHandler());
        specificationHandlerMap.put(GreaterThanEqual.class, new GreaterThanEqualPredicateHandler());
        specificationHandlerMap.put(GreaterThan.class, new GreaterThanPredicateHandler());
        specificationHandlerMap.put(LessThanEqual.class, new LessThanEqualPredicateHandler());
        specificationHandlerMap.put(LessThan.class, new LessThanPredicateHandler());
        specificationHandlerMap.put(Query.class, new QueryPredicateHandler());
        specificationHandlerMap.put(IsNull.class, new IsNullPredicateHandler());
    }

    /**
     * 根据查询的实体 Bean 参数中的 Fenix 相关的注解来构造 {@link Specification} 实例.
     *
     * @param beanParam 含有 Fenix 相关注解的 Java Bean 对象参数
     * @param <T>       范型 T
     * @return {@link Specification} 实例
     */
    public static <T> Specification<T> ofBean(Object beanParam) {
        return (root, query, builder) ->
                mergePredicates(builder, beanParamToPredicate(root, builder, beanParam)
                        .stream()
                        .collect(Collectors.groupingBy(Predicate::getOperator)));
    }

    /**
     * 根据动态条件列表中构造 {@link Specification} 实例.
     *
     * @param predicates 动态条件列表
     * @param <T>        范型 T
     * @return {@link Specification} 实例
     */
    public static <T> Specification<T> of(List<Predicate> predicates) {
        return (root, query, builder) ->
                mergePredicates(builder, predicates.stream().collect(Collectors.groupingBy(Predicate::getOperator)));
    }


    /**
     * 合并 AND 或者 OR 中的动态条件.
     *
     * @param builder       {@link CriteriaBuilder} 实例
     * @param predicatesMap 动态条件的 Map
     * @return 条件.
     */
    private static Predicate mergePredicates(CriteriaBuilder builder,
                                             Map<Predicate.BooleanOperator, List<Predicate>> predicatesMap) {
        List<Predicate> andPredicates = predicatesMap.get(Predicate.BooleanOperator.AND);
        List<Predicate> orPredicates = predicatesMap.get(Predicate.BooleanOperator.OR);
        if (CollectionUtil.isNotEmpty(andPredicates) && CollectionUtil.isNotEmpty(orPredicates)) {
            return builder.or(builder.and(andPredicates.toArray(new Predicate[0])),
                    builder.or(orPredicates.toArray(new Predicate[0])));
        } else if (CollectionUtil.isNotEmpty(orPredicates)) {
            return builder.or(orPredicates.toArray(new Predicate[0]));
        } else if (CollectionUtil.isNotEmpty(andPredicates)) {
            return builder.and(andPredicates.toArray(new Predicate[0]));
        } else {
            return null;
        }
    }

    /**
     * 将参数对象转换成 {@link Predicate} 对象集合.
     *
     * @param from            {@link From} 实例
     * @param criteriaBuilder {@link CriteriaBuilder} 实例
     * @param beanParam       对象参数
     * @param <Z>             范型 Z
     * @param <X>             范型 X
     * @return {@link Predicate} 对象集合
     */
    public static <Z, X> List<Predicate> beanParamToPredicate(
            From<Z, X> from, CriteriaBuilder criteriaBuilder, Object beanParam) {
        Field[] fields = ReflectUtil.getFields(beanParam.getClass());
        List<Predicate> predicates = new ArrayList<>(fields.length);
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            if (annotations == null) {
                continue;
            }

            for (Annotation annotation : annotations) {
                AbstractPredicateHandler handler = specificationHandlerMap.get(annotation.annotationType());
                if (handler != null) {
                    Predicate predicate = buildPredicate(beanParam, field, criteriaBuilder, from, handler);
                    if (predicate != null) {
                        predicates.add(predicate);
                    }
                }
            }
        }
        return predicates;
    }

    /**
     * 执行构建 {@link Predicate} 条件的方法.
     *
     * @param beanParam       含有 Fenix 相关注解的 Java Bean 对象参数
     * @param field           对应的字段
     * @param criteriaBuilder {@link CriteriaBuilder} 实例
     * @param root            {@link From} 实例
     * @param <Z>             范型 Z
     * @param <X>             范型 X
     * @return 一个 {@link Predicate} 实例
     */
    private static <Z, X> Predicate buildPredicate(Object beanParam, Field field, CriteriaBuilder criteriaBuilder,
                                                   From<Z, X> root, AbstractPredicateHandler handler) {
        Class<? extends Annotation> annotationClass = handler.getAnnotation();
        Annotation annotation = field.getAnnotation(annotationClass);
        if (annotation == null) {
            return null;
        }

        // 获取到 Java Bean 中的属性名称，并得到与其属性名的相同的方法，
        // 如果存在此方法就认为是用户定义了的用于判断属性条件是否匹配的方法，
        //     然后调用此方法，获取到布尔结果的值，为true, 则生成此属性的 Predicate 条件，否则不生成.
        // 如果不存在此方法，就使用属性值是否为空为判断依据，如果属性值不为空，就生成此属性的 Predicate 条件，否则不生成.
        boolean match;
        String propertyName = field.getName();
        try {
            Method propertyMethod = beanParam.getClass().getMethod(propertyName);
            propertyMethod.setAccessible(true);
            match = (boolean) propertyMethod.invoke(beanParam);
        } catch (NoSuchMethodException e) {
            // 如果不存在与属性名相同的自定义匹配规则的方法，就使用默认的"非空"匹配机制去生成 predicate 条件.
            Pair<String, Object> pair = getFieldNameAndValue(field, beanParam, annotation);
            return pair == null
                    ? null
                    : buildDefaultPredicate(criteriaBuilder, field, root, handler,
                    pair.getLeft(), pair.getRight(), annotation);
        } catch (IllegalAccessException e) {
            throw new SpecificationException("与属性名相同名称的 match 匹配方法，不能访问，"
                    + "请设置方法的访问级别为【public】，方法返回值类型为【boolean】类型.", e);
        } catch (InvocationTargetException e) {
            throw new SpecificationException("与属性名相同名称的 match 匹配方法，调用出错，"
                    + "请设置方法的访问级别为【public】，方法返回值类型为【boolean】类型，并检查其他引起调用失败的原因.", e);
        }

        // 如果不符合匹配规则，就直接返回 null.
        if (!match) {
            return null;
        }

        // 然后获取属性名称和值，如果为 null 直接返回 null，否则返回生成的 predicate 条件.
        Pair<String, Object> pair = getFieldNameAndValue(field, beanParam, annotation);
        return pair == null
                ? null
                : handler.buildPredicate(criteriaBuilder, root, pair.getLeft(), pair.getRight(), annotation);
    }

    /**
     * 从字段的注解中获取到字段的属性名称和值，存入到 {@link Pair} 对象实例中.
     *
     * @param field      属性字段
     * @param beanParam  含有 Fenix 相关注解的 Java Bean 对象参数
     * @param annotation 注解
     * @return 含有字段属性名称和值的 {@link Pair} 对象
     */
    private static Pair<String, Object> getFieldNameAndValue(Field field, Object beanParam, Annotation annotation) {
        PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(beanParam.getClass(), field.getName());
        if (descriptor == null) {
            return null;
        }

        // 获取到真正的数据库持久类 POJO 实体的属性值 fieldName 和该属性的值 value.
        try {
            String fieldName = (String) annotation.getClass().getMethod("value").invoke(annotation);
            fieldName = StrUtil.isBlank(fieldName) ? field.getName() : fieldName;
            return Pair.of(fieldName, descriptor.getReadMethod().invoke(beanParam));
        } catch (ReflectiveOperationException e) {
            throw new SpecificationException("构建【" + annotation.getClass().getName()
                    + "】注解的条件时，反射调用获取对应的属性字段值异常。", e);
        }
    }

    /**
     * 构建默认的没有自定义匹配方法时的 {@link Predicate} 条件实例.
     *
     * @param criteriaBuilder {@link CriteriaBuilder} 实例
     * @param field           字段
     * @param root            {@link From} 实例
     * @param handler         处理器
     * @param fieldName       数据库字段的实体属性名称
     * @param value           值
     * @param annotation      注解
     * @param <Z>             泛型 Z
     * @param <X>             泛型 X
     * @return {@link Predicate} 条件实例
     */
    private static <Z, X> Predicate buildDefaultPredicate(CriteriaBuilder criteriaBuilder, Field field,
                                                          From<Z, X> root, AbstractPredicateHandler handler, String fieldName, Object value, Annotation annotation) {
        return handler.buildPredicate(criteriaBuilder, root, fieldName, value, annotation);
    }

}
