package com.hb0730.poi.filedtype;


import com.hb0730.poi.annotation.ExcelField;

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
     * @param val        值
     * @param excelField 注解
     * @return .
     */
    default Object getValue(String val, ExcelField excelField) {
        return null;
    }

    /**
     * 设置值
     *
     * @param val        值
     * @param excelField 注解
     * @return .
     */
    default String setValue(Object val, ExcelField excelField) {
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
