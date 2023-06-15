package com.hb0730.boot.admin.modules.sys.monitor.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 定时任务在线管理
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/13
 */
@Data
@EqualsAndHashCode
@ToString
public class QuartzJobVO implements java.io.Serializable {
    @Schema(description = "主键")
    private String id;

    /**
     * 任务类名
     */
    @Schema(description = "任务类名")
    @NotBlank(message = "任务类名不能为空")
    private java.lang.String jobClassName;
    /**
     * 参数
     */
    @Schema(description = "参数")
    private java.lang.String parameter;
    /**
     * cron表达式
     */
    @Schema(description = "cron表达式")
    @NotBlank(message = "cron表达式不能为空")
    private java.lang.String cronExpression;

    /**
     * 状态 1正常 0停止
     */
    @Schema(description = "状态 1正常 0停止")
    private java.lang.Integer status;
    /**
     * 描述
     */
    @Schema(description = "描述")
    private java.lang.String description;
    /**
     * 创建人
     */
    @Schema(description = "创建人")
    protected String createdBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    protected LocalDateTime created;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    protected String modifiedBy;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    protected LocalDateTime modified;
}
