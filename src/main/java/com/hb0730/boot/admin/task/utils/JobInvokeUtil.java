package com.hb0730.boot.admin.task.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.hb0730.boot.admin.exceptions.JsonException;
import com.hb0730.boot.admin.task.domain.JobInvokeInfo;
import com.hb0730.commons.lang.collection.CollectionUtils;
import com.hb0730.commons.lang.convert.ConverterRegistry;
import com.hb0730.commons.lang.reflect.ReflectUtils;
import com.hb0730.jsons.SimpleJsonProxy;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 任务执行工具
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class JobInvokeUtil {
    /**
     * 调用任务方法
     *
     * @param job 定时任务信息
     */
    public static void invokeMethod(JobInvokeInfo job) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, JsonException {
        String bean = job.getBean();
        String method = job.getMethod();
        String params = job.getParams();
        invokeMethod(bean, method, params);
    }

    /**
     * 调用任务方法
     *
     * @param beanName   类名
     * @param methodName 方法名
     * @param params     方法参数
     */
    public static void invokeMethod(@NotBlank String beanName, @NotBlank String methodName, String params) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, JsonException {
        List<Object[]> methodParams = getMethodParams(params);
        if (SpringUtil.getApplicationContext().containsBean(beanName)) {
            Object o = SpringUtil.getBean(beanName);
            invokeMethod(o, methodName, methodParams);
        } else {
            Object o = Class.forName(beanName).getDeclaredConstructor().newInstance();
            invokeMethod(o, methodName, methodParams);

        }
    }

    /**
     * 调用任务方法
     *
     * @param bean         目标对象
     * @param methodName   方法名称
     * @param methodParams 方法参数
     * @throws InvocationTargetException,IllegalAccessException 调用异常
     */
    public static void invokeMethod(@Nonnull Object bean, @NotBlank String methodName, List<Object[]> methodParams) throws InvocationTargetException, IllegalAccessException {
        if (Objects.nonNull(methodParams) && methodParams.size() > 0) {
            Method method = ReflectUtils.getMethod(bean.getClass(), methodName, getMethodParamsType(methodParams));
            method.invoke(bean, getMethodParamsValue(methodParams));
        } else {
            Method method = ReflectUtils.getMethod(bean.getClass(), methodName);
            method.invoke(bean, getMethodParamsValue(methodParams));
        }
    }


    /**
     * 获取参数类型
     *
     * @param methodParams 参数相关列表
     * @return 参数类型列表
     */
    private static Class<?>[] getMethodParamsType(List<Object[]> methodParams) {
        Class<?>[] classs = new Class<?>[methodParams.size()];
        int index = 0;
        for (Object[] os : methodParams) {
            classs[index] = (Class<?>) os[1];
            index++;
        }
        return classs;
    }

    /**
     * 获取参数值
     *
     * @param methodParams 参数相关列表
     * @return 参数值列表
     */
    private static Object[] getMethodParamsValue(List<Object[]> methodParams) {
        Object[] classs = new Object[methodParams.size()];
        int index = 0;
        for (Object[] os : methodParams) {
            classs[index] = (Object) os[0];
            index++;
        }
        return classs;
    }

    private static List<Object[]> getMethodParams(String targetParams) throws ClassNotFoundException, JsonException {
        List<Object[]> classs = new LinkedList<>();
        if (StringUtils.isBlank(targetParams)) {
            return classs;
        }
        Map<String, Object> map = SimpleJsonProxy.json.fromJson(targetParams, new TypeReference<Map<String, Object>>() {
        });
        if (CollectionUtils.isEmpty(map)) {
            return classs;
        }
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            Class<?> clazz = Class.forName(key);
            // String
            if (clazz.getTypeName().equals(String.class.getTypeName())) {
                classs.add(new Object[]{String.valueOf(entry.getValue()), clazz});
                // Boolean
            } else if (clazz.getTypeName().equals(Boolean.class.getTypeName())) {
                classs.add(new Object[]{ConverterRegistry.getInstance().getDefaultConverter(Boolean.TYPE).convert(entry.getValue(), null), clazz});
                // Character
            } else if (clazz.getTypeName().equals(Character.class.getTypeName())) {
                classs.add(new Object[]{ConverterRegistry.getInstance().getDefaultConverter(Character.TYPE).convert(entry.getValue(), null), clazz});
                // Integer
            } else if (clazz.getTypeName().equals(Integer.class.getTypeName())) {
                classs.add(new Object[]{ConverterRegistry.getInstance().getDefaultConverter(Integer.TYPE).convert(entry.getValue(), null), clazz});
                // Byte
            } else if (clazz.getTypeName().equals(Byte.class.getTypeName())) {
                classs.add(new Object[]{ConverterRegistry.getInstance().getDefaultConverter(Byte.TYPE).convert(entry.getValue(), null), clazz});
                // Short
            } else if (clazz.getTypeName().equals(Short.class.getTypeName())) {
                classs.add(new Object[]{ConverterRegistry.getInstance().getDefaultConverter(Short.TYPE).convert(entry.getValue(), null), clazz});
                // Long
            } else if (clazz.getTypeName().equals(Long.class.getTypeName())) {
                if (StringUtils.containsIgnoreCase(entry.getValue().toString(), "L")) {
                    classs.add(new Object[]{ConverterRegistry.getInstance()
                        .getDefaultConverter(Long.TYPE)
                        .convert(StringUtils.replace(entry.getValue().toString(), "L", ""), null), clazz});
                } else {
                    classs.add(new Object[]{ConverterRegistry.getInstance().getDefaultConverter(Long.TYPE).convert(entry.getValue(), null), clazz});
                }
                // Float
            } else if (clazz.getTypeName().equals(Float.class.getTypeName())) {
                if (StringUtils.containsIgnoreCase(entry.getValue().toString(), "F")) {
                    classs.add(new Object[]{ConverterRegistry.getInstance().getDefaultConverter(Float.TYPE)
                        .convert(StringUtils.replaceIgnoreCase(entry.getValue().toString(), "F", ""), null), clazz});

                } else {
                    classs.add(new Object[]{ConverterRegistry.getInstance().getDefaultConverter(Float.TYPE).convert(entry.getValue(), null), clazz});
                }
                //Double
            } else if (clazz.getTypeName().equals(Double.class.getTypeName())) {
                if (StringUtils.containsIgnoreCase(entry.getValue().toString(), "D")) {
                    classs.add(new Object[]{ConverterRegistry.getInstance().getDefaultConverter(Double.TYPE)
                        .convert(StringUtils.replaceIgnoreCase(entry.getValue().toString(), "D", ""), null), clazz});

                } else {
                    classs.add(new Object[]{ConverterRegistry.getInstance().getDefaultConverter(Double.TYPE).convert(entry.getValue(), null), clazz});

                }
            } else {
                classs.add(new Object[]{BeanUtil.toBean(entry.getValue(), clazz), clazz});
            }
        }
        return classs;
    }
}
