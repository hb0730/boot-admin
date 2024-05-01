package com.hb0730.sys.domain.dto;

import com.hb0730.common.api.domain.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/30
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class QuartzJobDto extends BaseDto {
    @Schema(description = "任务ID")
    private String id;

    /**
     * 任务类名
     */
    @Schema(description = "任务名称")
    private String jobName;
    /**
     * 任务类名
     */

    @Schema(description = "任务类名orBean名称")
    private String jobClassName;
    /**
     * 参数
     */
    @Schema(description = "参数")
    private String parameter;

    /**
     * cron表达式
     */
    @Schema(description = "cron表达式")
    private String cronExpression;

    /**
     * 商户识别码
     */
    @Schema(description = "商户识别码(用于分组)")
    private String sysCode;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean enabled;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;
}
