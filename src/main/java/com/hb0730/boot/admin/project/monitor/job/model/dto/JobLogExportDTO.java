package com.hb0730.boot.admin.project.monitor.job.model.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.hb0730.boot.admin.commons.domain.model.domain.ExcelDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 任务日志导出
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class JobLogExportDTO extends ExcelDomain {
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
     * 定时任务id
     */
    @ExcelIgnore
    private Long jobId;
    /**
     * <p>
     * 任务编码
     * </p>
     */
    @ExcelProperty(value = "任务编码", index = 0)
    private String jobNumber;
    /**
     * 日志信息
     */
    @ExcelProperty(value = "日志信息", index = 1)
    private String jobMessage;

    /**
     * 任务状态
     */
    @ExcelProperty(value = "任务状态 (1 成功|2失败)", index = 2)
    private Integer status;

    /**
     * 异常信息
     */
    @ExcelProperty(value = "异常信息", index = 3)
    private String exceptionInfo;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间", index = 4)
    private Date startTime;

    /**
     * 停止时间
     */
    @ExcelProperty(value = "结束时间", index = 5)
    private Date stopTime;
}
