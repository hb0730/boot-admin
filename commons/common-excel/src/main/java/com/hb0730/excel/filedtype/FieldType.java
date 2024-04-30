package com.hb0730.excel.filedtype;

/**
 * 字段类型
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/9
 */
public interface FieldType {

    /**
     * 获取值
     *
     * @param val 值
     * @return .
     */
    default Object getValue(String val) {
        return null;
    }

    /**
     * 设置值
     *
     * @param val 值
     * @return .
     */
    default String setValue(Object val) {
        return null;
    }

    /**
     * 获取数据格式
     *
     * @return .
     */
    default String getDataFormat() {
        return null;
    }
}