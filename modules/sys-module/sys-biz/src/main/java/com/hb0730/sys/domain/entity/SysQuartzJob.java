package com.hb0730.sys.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.mybatis.core.domain.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/30
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString
@TableName("sys_quartz_job")
public class SysQuartzJob extends BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 任务类名
     */
    @NotBlank(message = "任务名不能为空")
    private String jobName;
    /**
     * 任务类名
     */

    @NotBlank(message = "任务类名/bean名称不能为空")
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
     * 商户识别码
     */
    private String sysCode;

    /**
     * 状态
     */
    @TableField("`is_enabled`")
    private Boolean enabled = true;

    /**
     * 描述
     */
    private String description;
}
