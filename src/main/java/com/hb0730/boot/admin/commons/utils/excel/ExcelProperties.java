package com.hb0730.boot.admin.commons.utils.excel;

import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@AllArgsConstructor
public class ExcelProperties {
    private String path;
    private String fileName;
    private ExcelTypeEnum excelType;
    private Class<?> clazz;

}
