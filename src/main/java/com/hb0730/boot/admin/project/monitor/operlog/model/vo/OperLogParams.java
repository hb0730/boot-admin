package com.hb0730.boot.admin.project.monitor.operlog.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
public class OperLogParams implements Serializable {
    /**
     * 操作模块
     */
    private String module;

    /**
     * 操作类型
     */
    private Integer businessType;

    /**
     * 操作账号
     */
    private String username;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 操作开始时间
     */
    private Date startTime;

    /**
     * 操作结束时间
     */
    private Date endTime;
}
