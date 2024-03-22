package com.hb0730.base.utils;

import com.hb0730.base.base.BasePageQuery;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/14
 */
@Slf4j
public class QueryHelper {
    /**
     * 获取所有字段
     *
     * @param clazz  类
     * @param fields 字段
     * @return 字段
     */
    public static List<Field> getAllFields(Class<?> clazz, List<Field> fields) {
        if (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            getAllFields(clazz.getSuperclass(), fields);
        }
        return fields;
    }


    /**
     * 设置字段为空
     *
     * @param query        查询条件
     * @param fieldIsNull  字段为空
     * @param ignoreFields 忽略字段
     * @param <T>          查询条件
     */
    public static <T extends BasePageQuery> void setFieldNull(T query, Map<String, String> fieldIsNull,
                                                              String... ignoreFields) {
        List<Field> fields = getAllFields(query.getClass(), new ArrayList<>());
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object val = field.get(query);
                // 忽略字段
                if (Arrays.asList(ignoreFields).contains(field.getName())) {
                    continue;
                }
                //如果其他字段为空，则设置字段为空
                if (val != null) {
                    fields.stream().filter(e -> fieldIsNull.containsKey(e.getName())).forEach(e -> {
                        try {
                            e.setAccessible(true);
                            e.set(query, null);
                        } catch (IllegalAccessException illegalAccessException) {
                            log.error("设置字段为空失败", illegalAccessException);
                            throw new RuntimeException("设置字段为空失败");
                        }
                    });
                    break;
                }
                fields.stream().filter(e -> fieldIsNull.containsKey(e.getName())).forEach(e -> {
                    try {
                        e.setAccessible(true);
                        e.set(query, fieldIsNull.get(e.getName()));
                    } catch (IllegalAccessException illegalAccessException) {
                        log.error("设置字段为空失败", illegalAccessException);
                        throw new RuntimeException("设置字段为空失败");
                    }
                });

            } catch (IllegalAccessException e) {
                log.error("设置字段为空失败", e);
                throw new RuntimeException("设置字段为空失败");
            }
        }
    }
}
