package com.hb0730.boot.admin.project.system.quartz.model.dto;

import com.hb0730.boot.admin.domain.model.InputConverter;
import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import com.hb0730.boot.admin.project.system.quartz.model.entity.JobEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 定时任务(quartz)
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class JobDTO extends BaseDTO implements InputConverter<JobEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    private String description;

    /**
     * id
     */
    private Long id;

    /**
     * 任务名称
     */
    @NotBlank(message = "任务名称不为空")
    private String name;

    /**
     * 任务组
     */
    @NotBlank(message = "任务组不为空")
    private String group;

    /**
     * Bean名称
     */
    @NotBlank(message = "调用类不为空")
    private String beanName;

    /**
     * 方法名称
     */
    @NotBlank(message = "调用方法不为空")
    private String beanMethod;

    /**
     * 参数
     */
    private String methodParams;

    /**
     * cron表达式
     */
    @NotBlank(message = "cron表达式不为空")
    private String cron;

    /**
     * 负责人
     */
    private String personInCharge;

    /**
     * 负责人邮箱
     */
    private String email;
}
