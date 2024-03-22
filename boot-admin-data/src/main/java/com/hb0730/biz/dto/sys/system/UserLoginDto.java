package com.hb0730.biz.dto.sys.system;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录-数据传输对象
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/23
 */
@Data
@EqualsAndHashCode
public class UserLoginDto implements Serializable {
    private Integer id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;
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
     * 是否管理员
     */
    private Boolean admin;

    public boolean isAdmin() {
        return Boolean.TRUE.equals(this.admin);
    }

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    /**
     * 最近修改密码时间
     */
    private Date pwdResetTime;
    /**
     * 状态
     * <p>
     * 1 正常
     * 0 禁用
     */
    private Boolean enabled;

    public boolean isEnabled() {
        return Boolean.TRUE.equals(this.enabled);
    }

    /**
     * 创建人
     */
    protected String createdBy;

    /**
     * 创建时间
     */
    protected java.util.Date created;

    /**
     * 更新人
     */
    protected String modifiedBy;

    /**
     * 更新时间
     */
    protected java.util.Date modified;
}
