package com.hb0730.boot.admin.project.monitor.logininfo.model.vo;

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
public class LoginfoParams implements Serializable {
    /**
     * 登录用户
     */
    private String username;

    /**
     * 登录ip
     */
    private String ipaddr;

    /**
     * 登录状态
     */
    private Integer status;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;
}
