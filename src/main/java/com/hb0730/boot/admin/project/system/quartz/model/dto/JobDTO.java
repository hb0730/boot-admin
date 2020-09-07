package com.hb0730.boot.admin.project.system.quartz.model.dto;

import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 定时任务(quartz)
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class JobDTO extends BaseDTO {

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
    private String name;

    /**
     * 任务组
     */
    private String group;

    /**
     * Bean名称
     */
    private String beanName;

    /**
     * 方法名称
     */
    private String beanMethod;

    /**
     * 参数
     */
    private String methodParams;

    /**
     * cron表达式
     */
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
