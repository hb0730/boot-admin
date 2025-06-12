package com.hb0730.poi.model;

import lombok.Data;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/12/25
 */
@Data
public class ImportParams {
    /**
     * 从第几行开始读取
     */
    private Integer startRow = 1;

    /**
     * 从第几sheet开始读取
     */
    private Integer sheetIndex = 0;

    /**
     * 读取sheet数量
     */
    private Integer sheetNum = 1;
}
