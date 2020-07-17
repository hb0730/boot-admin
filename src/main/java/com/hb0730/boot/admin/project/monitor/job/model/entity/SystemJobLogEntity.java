package com.hb0730.boot.admin.project.monitor.job.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.domain.model.domain.BusinessDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 定时任务日志
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_system_job_log")
public class SystemJobLogEntity extends BusinessDomain {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * id
     */
    @TableId("id")
    private Long id;

    /**
     * 定时任务id
     */
    @TableField("job_id")
    private Long jobId;

    /**
     * 日志信息
     */
    @TableField("job_message")
    private String jobMessage;

    /**
     * 任务状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 异常信息
     */
    @TableField("exception_info")
    private String exceptionInfo;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 停止时间
     */
    @TableField("stop_time")
    private Date stopTime;


    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String JOB_ID = "job_id";

    public static final String JOB_MESSAGE = "job_message";

    public static final String STATUS = "status";

    public static final String EXCEPTION_INFO = "exception_info";

    public static final String START_TIME = "start_time";

    public static final String STOP_TIME = "stop_time";

}
