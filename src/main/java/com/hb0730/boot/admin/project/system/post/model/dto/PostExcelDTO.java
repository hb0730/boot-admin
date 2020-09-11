package com.hb0730.boot.admin.project.system.post.model.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.hb0730.boot.admin.domain.model.excel.ExcelDomain;
import lombok.*;

import java.util.Date;

/**
 * 岗位 excel 实体
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostExcelDTO extends ExcelDomain {
    /**
     * 编码
     */
    @ExcelProperty(value = "岗位编码", index = 0)
    private String number;
    /**
     * 名称
     */
    @ExcelProperty(value = "岗位名称", index = 1)
    private String name;

    /**
     * 岗位状态
     */
    @ExcelProperty(value = "状态(1启用|0禁用)", index = 2)
    private Integer isEnabled;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间", index = 3)
    @DateTimeFormat(value = "yyyy-MM-dd HH:ss:mm")
    private Date createTime;

}
