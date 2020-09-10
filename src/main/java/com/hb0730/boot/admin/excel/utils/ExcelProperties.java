package com.hb0730.boot.admin.excel.utils;

import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * excel properties
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@AllArgsConstructor
public class ExcelProperties {
    /**
     * 文件路径
     */
    private String path;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * excel类型
     */
    private ExcelTypeEnum excelType;
    /**
     * 注解实体类
     */
    private Class<?> clazz;
}
