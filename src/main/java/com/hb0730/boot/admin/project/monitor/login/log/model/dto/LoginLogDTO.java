package com.hb0730.boot.admin.project.monitor.login.log.model.dto;

import com.hb0730.boot.admin.domain.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登录日志
 *
 * @author bing_huang
 * @since 3.0.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginLogDTO extends BaseDTO {

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
     * 登录名
     */
    private String username;

    /**
     * 登录ip
     */
    private String loginIp;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登录状态
     */
    private Integer status;

    /**
     * 登录信息
     */
    private String message;
}
