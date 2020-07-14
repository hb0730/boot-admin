package com.hb0730.boot.admin.project.monitor.job.model.vo;

import com.hb0730.boot.admin.commons.web.model.BusinessVO;
import com.hb0730.boot.admin.project.monitor.job.model.entity.SystemJobEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 定时任务
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemJobVO extends BusinessVO<SystemJobEntity> {

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
     * number
     */
    private String number;

    /**
     * name
     */
    private String name;

    /**
     * 调用目标
     */
    @NotBlank(message = "调用不为空")
    private String beanName;

    /**
     * 调用方法
     */
    @NotBlank(message = "调用方法不为空")
    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * 表达式
     */
    @NotBlank(message = "表达式不为空")
    private String cron;
}
