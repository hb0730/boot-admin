package com.hb0730.biz.dto.sys.quartz;

import com.hb0730.data.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/14
 */
@Getter
@Setter
@Accessors(chain = true)
public class QuartzJobDto extends BaseDto {
    private Integer id;
    /**
     * 任务类名
     */
    @NotBlank(message = "任务类名不能为空")
    private String jobName;
    /**
     * 任务类名
     */
    @NotBlank(message = "任务类名不能为空")
    private String jobClassName;
    
    /**
     * 参数
     */
    private String parameter;

    /**
     * cron表达式
     */
    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;

    /**
     * 状态 0正常 -1停止
     */
    private Boolean enabled;

    /**
     * 描述
     */
    private String description;
}
