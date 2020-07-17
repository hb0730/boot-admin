package com.hb0730.boot.admin.project.monitor.job.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hb0730.boot.admin.utils.convert.InputConverter;
import com.hb0730.boot.admin.domain.model.web.BusinessVO;
import com.hb0730.boot.admin.project.monitor.job.model.entity.SystemJobLogEntity;
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
public class SystemJobLogVO extends BusinessVO implements InputConverter<SystemJobLogEntity> {

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
     * 定时任务id
     */
    private Long jobId;

    /**
     * 日志信息
     */
    private String jobMessage;

    /**
     * 任务状态
     */
    private Integer status;

    /**
     * 异常信息
     */
    private String exceptionInfo;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 停止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date stopTime;
}
