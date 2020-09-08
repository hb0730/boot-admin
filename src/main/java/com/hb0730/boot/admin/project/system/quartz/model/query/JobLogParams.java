package com.hb0730.boot.admin.project.system.quartz.model.query;

import com.hb0730.boot.admin.domain.model.query.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 任务日志过滤
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class JobLogParams extends BaseParams {

    private Long jobId;

    private String jobName;
    private String jobGroup;
    private Integer status;
    private Date startTime;
    private Date endTime;
}
