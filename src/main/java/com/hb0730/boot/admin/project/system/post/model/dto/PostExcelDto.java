package com.hb0730.boot.admin.project.system.post.model.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.hb0730.boot.admin.commons.domain.model.domain.ExcelDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 岗位信息excel
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PostExcelDto extends ExcelDomain {
    /**
     * 备注
     */
    @ExcelProperty(value = "备注", index = -1)
    private String description;

    /**
     * id
     */
    @ExcelIgnore
    private Long id;

    /**
     * 岗位编码
     */
    @ExcelProperty(value = "岗位编码", index = 0)
    private String number;
    /**
     * 岗位名称
     */
    @ExcelProperty(value = "岗位名称", index = 1)
    private String name;

    /**
     * 岗位英文名称
     */
    @ExcelProperty(value = "岗位英文名称", index = 2)
    private String enname;


    /**
     * 排序
     */
    @ExcelProperty(value = "排序", index = 3)
    private Integer sort;
    /**
     * 状态(启用禁用状态)
     */
    @ExcelProperty(value = "状态(1 启用|0 禁用)", index = 4)
    private Integer isEnabled;
}
