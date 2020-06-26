package com.hb0730.boot.admin.project.monitor.logininfo.model.vo;

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
@EqualsAndHashCode(callSuper = true)
@ToString
public class LoginfoParams extends BaseParams {
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
