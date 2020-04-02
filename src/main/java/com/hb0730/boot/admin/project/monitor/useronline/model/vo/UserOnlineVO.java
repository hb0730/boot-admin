package com.hb0730.boot.admin.project.monitor.useronline.model.vo;

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
public class UserOnlineVO implements Serializable {
    /**
     * 会话id
     */
    private String tokenId;
    /**
     * 登录账号
     */
    private String  username;

    /** 登录IP地址 */
    private String ipaddr;

    /** 登录地址 */
    private String loginLocation;

    /** 浏览器类型 */
    private String browser;

    /** 操作系统 */
    private String os;

    /** 登录时间 */
    private Long loginTime;
}
