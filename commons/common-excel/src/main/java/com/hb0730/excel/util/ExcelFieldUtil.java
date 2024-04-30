package com.hb0730.excel.util;

import cn.hutool.core.util.ReflectUtil;
import com.hb0730.excel.annotation.ExcelField;
import com.hb0730.excel.annotation.ExcelFields;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * excel字段工具类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/10
 */
public class ExcelFieldUtil {

    /**
     * 获取注解值
     *
     * @param clazz 类
     * @return 注解值
     */
    public static List<Object[]> getAnnotationValue(Class<?> clazz) {
        List<Object[]> annotationList = new ArrayList<>();

        //获取类上的注解
        ExcelFields excelFields = clazz.getAnnotation(ExcelFields.class);
        if (null != excelFields) {
            ExcelField[] fields = excelFields.value();
            for (ExcelField field : fields) {
                annotationList.add(new Object[]{field, null});
            }
        }


        //获取字段
        Field[] fields = ReflectUtil.getFields(clazz);
        for (Field field : fields) {
            ExcelField excelField = field.getAnnotation(ExcelField.class);
            if (null != excelField) {
                annotationList.add(new Object[]{excelField, field});
            }
        }

        // 获取方法上的注解
        Method[] methods = ReflectUtil.getMethods(clazz);
        for (Method method : methods) {
            ExcelField excelField = method.getAnnotation(ExcelField.class);
            if (null != excelField) {
                annotationList.add(new Object[]{excelField, method});
            }
        }

        //排序
        annotationList.sort((o1, o2) -> {
            ExcelField excelField1 = (ExcelField) o1[0];
            ExcelField excelField2 = (ExcelField) o2[0];
            return excelField1.order() - excelField2.order();
        });
        return annotationList;
    }
}