package com.hb0730.boot.admin.project.monitor.logininfo.model.vo;

import com.hb0730.boot.admin.commons.web.model.BusinessVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统登录日志
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemLoginfoVO extends BusinessVO {

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
     * 用户账号
     */
    private String username;

    /**
     * 登录状态
     */
    private Integer status;

    /**
     * 登录地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 提示信息
     */
    private String message;
}
