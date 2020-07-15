package com.hb0730.boot.admin.project.monitor.job.model.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.hb0730.boot.admin.commons.domain.model.domain.ExcelDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * 定时任务导出
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class JobExportDto extends ExcelDomain {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @ExcelIgnore
    private Long id;
    /**
     * 备注
     */
    @ExcelProperty(value = "备注", index = -1)
    private String description;

    /**
     * number
     */
    @ExcelProperty(value = "编码", index = 0)
    private String number;

    /**
     * name
     */
    @ExcelProperty(value = "名称", index = 1)
    private String name;

    /**
     * 调用目标
     */
    @ExcelProperty(value = "调用类", index = 2)
    private String beanName;

    /**
     * 调用方法
     */
    @ExcelProperty(value = "调用方法", index = 3)
    private String methodName;

    /**
     * 参数
     */
    @ExcelProperty(value = "调用参数", index = 4)
    private String params;

    /**
     * 表达式
     */
    @ExcelProperty(value = "表达式", index = 5)
    private String cron;

    /**
     * 是否启用
     */
    @ExcelProperty(value = "状态(1 启用|0 禁用)")
    private Integer isEnabled;
}
