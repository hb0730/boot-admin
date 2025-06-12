package com.hb0730.operator.log.core.factory;


import com.hb0730.operator.log.core.model.OperatorType;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作类型持有者
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
public class OperatorTypeHolder {

    private static final Map<String, OperatorType> TYPES = new HashMap<>();

    private OperatorTypeHolder() {
    }

    /**
     * 获取类型
     *
     * @param key key
     * @return type
     */
    public static OperatorType get(String key) {
        return TYPES.get(key);
    }

    /**
     * 设置类型
     *
     * @param type type
     */
    public static void set(OperatorType type) {
        TYPES.put(type.getType(), type);
    }
}
