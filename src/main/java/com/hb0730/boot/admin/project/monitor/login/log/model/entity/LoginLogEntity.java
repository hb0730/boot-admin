package com.hb0730.boot.admin.project.monitor.login.log.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.boot.admin.domain.model.entity.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 登录日志
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_login_log")
public class LoginLogEntity extends BaseDomain {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * id
     */
    @TableId("id")
    private Long id;

    /**
     * 登录名
     */
    @TableField("username")
    private String username;

    /**
     * 登录ip
     */
    @TableField("login_ip")
    private String loginIp;

    /**
     * 浏览器
     */
    @TableField("browser")
    private String browser;

    /**
     * 操作系统
     */
    @TableField("os")
    private String os;

    /**
     * 登录状态
     */
    @TableField("`status`")
    private Integer status;

    /**
     * 登录信息
     */
    @TableField("message")
    private String message;


    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String USERNAME = "username";

    public static final String LOGIN_IP = "login_ip";

    public static final String BROWSER = "browser";

    public static final String OS = "os";

    public static final String STATUS = "`status`";

    public static final String MESSAGE = "message";

}
