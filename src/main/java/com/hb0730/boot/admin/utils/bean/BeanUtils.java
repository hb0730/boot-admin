package com.hb0730.boot.admin.utils.bean;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class BeanUtils {
    /**
     * 从源对象转换。（仅复制相同属性）
     *
     * @param source      源对象
     * @param targetClass 目标对象
     * @param <T>         目标类型
     * @return 目标对象
     */
    @Nullable
    public static <T> T transformFrom(@Nullable Object source, @NonNull Class<T> targetClass) {
        Assert.notNull(targetClass, "Target class must not be null");

        if (source == null) {
            return null;
        }

        // Init the instance
        try {
            // New instance for the target class
            T targetInstance = targetClass.newInstance();
            // Copy properties
            org.springframework.beans.BeanUtils.copyProperties(source, targetInstance, getNullPropertyNames(source));
            // Return the target instance
            return targetInstance;
        } catch (Exception e) {
            throw new BeanInstantiationException(targetClass, "Failed to new " + targetClass.getName() + " instance or copy properties", e);
        }
    }

    /**
     * 转换源数据集合
     *
     * @param sources     源对象集
     * @param targetClass 目标对象
     * @param <T>         目标对象类型
     * @return 从源数据集合转换目标集合
     */
    @NonNull
    public static <T> List<T> transformFromInBatch(Collection<?> sources, @NonNull Class<T> targetClass) {
        if (CollectionUtils.isEmpty(sources)) {
            return Collections.emptyList();
        }

        // Transform in batch
        return sources.stream()
                .map(source -> transformFrom(source, targetClass))
                .collect(Collectors.toList());
    }

    /**
     * 更新非空属性
     *
     * @param source 源目标
     * @param target 对象目标
     */
    public static void updateProperties(@NonNull Object source, @NonNull Object target) {
        Assert.notNull(source, "source object must not be null");
        Assert.notNull(target, "target object must not be null");

        // Set non null properties from source properties to target properties
        org.springframework.beans.BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    /**
     * 获取属性的空名称集
     *
     * @param source 源对象
     * @return 为nul值的属性
     */
    @NonNull
    private static String[] getNullPropertyNames(@NonNull Object source) {
        return getNullPropertyNameSet(source).toArray(new String[0]);
    }

    /**
     * 获取属性的空名称集
     *
     * @param source 源对象
     * @return 为nul值的属性
     */
    @NonNull
    private static Set<String> getNullPropertyNameSet(@NonNull Object source) {

        Assert.notNull(source, "source object must not be null");
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = beanWrapper.getPropertyValue(propertyName);

            // if property value is equal to null, add it to empty name set
            if (propertyValue == null) {
                emptyNames.add(propertyName);
            }
        }

        return emptyNames;
    }

    /**
     * <p>
     * 把Map转化为JavaBean
     * </p>
     *
     * @param map 源目标
     * @param clz 对象目标
     * @param <T> 对象类型
     * @return 对象模板
     * @throws Exception
     */
    public static <T> T map2bean(Map<String, Object> map, Class<T> clz) throws Exception {
        //创建一个需要转换为的类型的对象
        T obj = clz.newInstance();
        //从Map中获取和属性名称一样的值，把值设置给对象(setter方法)

        //得到属性的描述器
        BeanInfo b = Introspector.getBeanInfo(clz, Object.class);
        PropertyDescriptor[] pds = b.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            //得到属性的setter方法
            Method setter = pd.getWriteMethod();
            //得到key名字和属性名字相同的value设置给属性
            setter.invoke(obj, map.get(pd.getName()));
        }
        return obj;
    }
}
