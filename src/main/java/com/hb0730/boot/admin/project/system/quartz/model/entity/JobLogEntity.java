package com.hb0730.boot.admin.project.system.quartz.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 任务日志
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_job_log")
public class JobLogEntity extends BaseDomain {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务id
     */
    @TableField("job_id")
    private Long jobId;

    /**
     * 任务名称
     */
    @TableField("job_name")
    private String jobName;

    /**
     * 任务组
     */
    @TableField("job_group")
    private String jobGroup;

    /**
     * 调用目标
     */
    @TableField("invoke_target")
    private String invokeTarget;

    /**
     * 调用参数
     */
    @TableField("method_params")
    private String methodParams;

    /**
     * 日志信息
     */
    @TableField("job_message")
    private String jobMessage;

    /**
     * 状态 0失败1成功
     */
    @TableField("`status`")
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
     * 结束时间
     */
    @TableField("end_time")
    private Date endTime;


    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String JOB_ID = "job_id";

    public static final String JOB_NAME = "job_name";

    public static final String JOB_GROUP = "job_group";

    public static final String INVOKE_TARGET = "invoke_target";

    public static final String METHOD_PARAMS = "method_params";

    public static final String JOB_MESSAGE = "job_message";

    public static final String STATUS = "status";

    public static final String EXCEPTION_INFO = "exception_info";

    public static final String START_TIME="start_time";

    public static final String END_TIME="end_time";
}
