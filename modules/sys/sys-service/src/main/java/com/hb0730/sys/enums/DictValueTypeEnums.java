package com.hb0730.sys.enums;

import java.math.BigDecimal;

/**
 * 数据字典值类型
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/12
 */
public enum DictValueTypeEnums {
    /**
     * 字符串
     */
    STRING,

    /**
     * 整数
     */
    INTEGER {
        @Override
        public Object parse(String s) {
            try {
                return Integer.valueOf(s);
            } catch (Exception e) {
                return super.parse(s);
            }
        }

        public Class<?> getType() {
            return Integer.class;
        }
    },

    /**
     * 小数
     */
    DECIMAL {
        @Override
        public Object parse(String s) {
            try {
                return BigDecimal.valueOf(Double.valueOf(s));
            } catch (Exception e) {
                return super.parse(s);
            }
        }

        public Class<?> getType() {
            return BigDecimal.class;
        }
    },

    /**
     * 布尔值
     */
    BOOLEAN {
        @Override
        public Object parse(String s) {
            try {
                return Boolean.valueOf(s);
            } catch (Exception e) {
                return super.parse(s);
            }
        }

        public Class<?> getType() {
            return Boolean.class;
        }
    },

    /**
     * 颜色
     */
    COLOR,

    ;

    /**
     * 转换
     *
     * @param s s
     * @return value
     */
    public Object parse(String s) {
        return s;
    }

    /**
     * 获取类型
     *
     * @return 类型
     */
    public Class<?> getType() {
        return String.class;
    }

    public static DictValueTypeEnums of(String type) {
        if (type == null) {
            return STRING;
        }
        for (DictValueTypeEnums value : values()) {
            if (value.name().equals(type)) {
                return value;
            }
        }
        return STRING;
    }
}
