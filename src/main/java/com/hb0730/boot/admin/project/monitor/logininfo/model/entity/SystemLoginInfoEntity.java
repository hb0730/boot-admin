package com.hb0730.boot.admin.project.monitor.logininfo.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hb0730.boot.admin.commons.domain.BusinessDomain;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("t_system_login_info")
public class SystemLoginInfoEntity extends BusinessDomain {

    private static final long serialVersionUID=1L;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户账号
     */
    @TableField("username")
    private String username;

    /**
     * 登录状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 登录地址
     */
    @TableField("ipaddr")
    private String ipaddr;

    /**
     * 登录地点
     */
    @TableField("login_location")
    private String loginLocation;

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
     * 提示信息
     */
    @TableField("message")
    private String message;


    public static final String DESCRIPTION = "description";

    public static final String ID = "id";

    public static final String USERNAME = "username";

    public static final String STATUS = "status";

    public static final String IPADDR = "ipaddr";

    public static final String LOGIN_LOCATION = "login_location";

    public static final String BROWSER = "browser";

    public static final String OS = "os";

    public static final String MESSAGE = "message";

}
