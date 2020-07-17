package com.hb0730.boot.admin.task.utils;

import com.hb0730.boot.admin.utils.bean.BeanUtils;
import com.hb0730.boot.admin.utils.json.GsonUtils;
import com.hb0730.boot.admin.utils.spring.SpringUtils;
import com.hb0730.boot.admin.task.spring.TaskConstant;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotBlank;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <p>
 * 任务执行工具
 * </P>
 *
 * @author bing_huang
 * @since V2.0
 */
public class JobInvokeUtil {

    /**
     * 调用任务方法
     *
     * @param constant 定时任务信息
     */
    public static void invokeMethod(TaskConstant constant) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String beanName = constant.getBeanName();
        String methodName = constant.getMethodName();
        String params = constant.getParams();
        invokeMethod(beanName, methodName, params);
    }

    /**
     * 调用任务方法
     *
     * @param beanName   类名
     * @param methodName 方法名
     * @param params     方法参数
     */
    public static void invokeMethod(@NotBlank String beanName, @NotBlank String methodName, String params) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Object[]> methodParams = getMethodParams(params);
        if (SpringUtils.containsBean(beanName)) {
            Object o = SpringUtils.getBean(beanName);
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
     */
    public static void invokeMethod(Object bean, String methodName, List<Object[]> methodParams) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (Objects.nonNull(methodParams) && methodParams.size() > 0) {
            Method method = bean.getClass().getDeclaredMethod(methodName, getMethodParamsType(methodParams));
            method.invoke(bean, getMethodParamsValue(methodParams));
        } else {
            Method method = bean.getClass().getDeclaredMethod(methodName);
            method.invoke(bean);
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

    /**
     * 获取method方法参数相关列表
     *
     * @param targetParams 目标字符串
     * @return method方法相关参数列表
     */
    private static List<Object[]> getMethodParams(String targetParams) throws ClassNotFoundException {
        List<Object[]> classs = new LinkedList<>();
        Map<String, Object> map = GsonUtils.jsonToMaps(targetParams);
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
                classs.add(new Object[]{Boolean.valueOf(entry.getValue().toString()), clazz});
                // Character
            } else if (clazz.getTypeName().equals(Character.class.getTypeName())) {
                classs.add(new Object[]{CharUtils.toChar(entry.getValue().toString()), clazz});
                // Integer
            } else if (clazz.getTypeName().equals(Integer.class.getTypeName())) {
                classs.add(new Object[]{Integer.valueOf(entry.getValue().toString()), clazz});
                // Byte
            } else if (clazz.getTypeName().equals(Byte.class.getTypeName())) {
                classs.add(new Object[]{Byte.valueOf(entry.getValue().toString()), clazz});
                // Short
            } else if (clazz.getTypeName().equals(Short.class.getTypeName())) {
                classs.add(new Object[]{Short.valueOf(entry.getValue().toString()), clazz});
                // Long
            } else if (clazz.getTypeName().equals(Long.class.getTypeName())) {
                if (StringUtils.containsIgnoreCase(entry.getValue().toString(), "L")) {
                    classs.add(new Object[]{Long.valueOf(StringUtils.replaceIgnoreCase(entry.getValue().toString(), "L", "")), clazz});
                } else {
                    classs.add(new Object[]{Long.valueOf(entry.getValue().toString()), clazz});
                }
                // Float
            } else if (clazz.getTypeName().equals(Float.class.getTypeName())) {
                if (StringUtils.containsIgnoreCase(entry.getValue().toString(), "F")) {
                    classs.add(new Object[]{Float.valueOf(StringUtils.replaceIgnoreCase(entry.getValue().toString(), "F", "")), clazz});

                } else {
                    classs.add(new Object[]{Float.valueOf(entry.getValue().toString()), clazz});
                }
                //Double
            } else if (clazz.getTypeName().equals(Double.class.getTypeName())) {
                if (StringUtils.containsIgnoreCase(entry.getValue().toString(), "D")) {
                    classs.add(new Object[]{Double.valueOf(StringUtils.replaceIgnoreCase(entry.getValue().toString(), "D", "")), clazz});

                } else {
                    classs.add(new Object[]{Double.valueOf(entry.getValue().toString()), clazz});

                }
            } else {
                classs.add(new Object[]{BeanUtils.transformFrom(entry.getValue(), clazz), clazz});
            }
        }
        return classs;
    }
}
