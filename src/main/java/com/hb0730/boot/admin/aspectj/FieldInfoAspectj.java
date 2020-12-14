package com.hb0730.boot.admin.aspectj;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.annotation.FieldClass;
import com.hb0730.boot.admin.annotation.FieldInfo;
import com.hb0730.commons.lang.reflect.ReflectUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author bing_huang
 */
public class FieldInfoAspectj {

    /**
     * 获取所有的属性注解，包含父类
     *
     * @param target 目标class
     * @return key, value形式的集合，key为 {@link FieldInfo#columnName()},value为 {@link FieldInfo#name()},
     */
    public static Map<String, String> getFieldInfo(Class<?> target) {
        List<Field> fields = ReflectUtils.getFields(target, true);
        FieldClass annotation = target.getAnnotation(FieldClass.class);
        List<String> exclude = Lists.newArrayList();
        if (null != annotation) {
            exclude = Arrays.asList(annotation.exclude());
        }
        Map<String, String> resultMap = Maps.newHashMap();
        for (Field field : fields) {
            FieldInfo fieldInfos = field.getAnnotation(FieldInfo.class);
            if (null == fieldInfos) {
                continue;
            }
            if (exclude.contains(fieldInfos.columnName())) {
                continue;
            }
            resultMap.put(fieldInfos.columnName(), fieldInfos.name());
        }
        return resultMap;
    }
}
