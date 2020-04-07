package com.hb0730.boot.admin.project.monitor.job.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
public class JobParams implements Serializable {

    /**
     * 任务编码
     */
    private String number;

    /**
     * 任务名称
     */
    private String name;
    /**
     * 是否启用
     */
    private Integer enabled;
}
