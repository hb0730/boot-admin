package com.hb0730.boot.admin.project.monitor.job.model.vo;

import com.hb0730.boot.admin.commons.web.model.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class JobLogParams extends BaseParams {
    /**
     * 任务id
     */
    private Long jobId;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务状态
     */
    private Integer status;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date stopTime;
}
