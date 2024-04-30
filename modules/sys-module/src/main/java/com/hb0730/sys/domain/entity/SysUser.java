package com.hb0730.sys.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hb0730.mybatis.core.domain.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString
@TableName("sys_user")
public class SysUser extends BaseEntity {
    /**
     * 用户id
     */
    private String id;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * 性别 0 未知 1 男 2 女
     */
    private Integer gender = 0;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    /**
     * 最后登录ip
     */
    private String lastLoginIp;
    /**
     * 最后修改密码的时间
     */
    private Date lastPwdResetTime;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否系统用户
     */
//    @TableField("`is_system`")
//    private Boolean system = false;
    private Boolean isSystem = false;
    @TableField(exist = false)
    private Boolean system = isSystem;

    public void setSystem(Boolean system) {
        this.system = system;
        this.isSystem = system;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
        this.system = isSystem;
    }

    /**
     * 状态
     */
    @TableField("`is_enabled`")
    private Boolean enabled = true;

}
