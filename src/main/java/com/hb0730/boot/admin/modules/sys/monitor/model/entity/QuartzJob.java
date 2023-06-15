package com.hb0730.boot.admin.modules.sys.monitor.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.data.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 定时任务在线管理表
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@TableName("sys_quartz_job")
public class QuartzJob extends BaseEntity {
    /**
     * 任务类名
     */
    private java.lang.String jobClassName;
    /**
     * 参数
     */
    private java.lang.String parameter;
    /**
     * cron表达式
     */
    private java.lang.String cronExpression;

    /**
     * 状态 1正常 0停止
     */
    private java.lang.Integer status;
    /**
     * 描述
     */
    private java.lang.String description;
}
