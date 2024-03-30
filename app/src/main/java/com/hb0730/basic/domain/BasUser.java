package com.hb0730.basic.domain;

import com.hb0730.base.jpa.core.domain.TenantBaseEntity;
import com.hb0730.base.jpa.core.id.IdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "bas_user")
public class BasUser extends TenantBaseEntity {
    @Id
    @IdGenerator
    private String id;
    /**
     * 组织
     */
    @OneToOne
    @JoinColumn(name = "org_id")
    private BasOrg org;
    /**
     * 角色
     */
    @ManyToMany(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinTable(name = "sys_user_role",
            joinColumns = @jakarta.persistence.JoinColumn(name = "user_id"),
            inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "role_id"))
    private List<BasRole> roles;

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
    private String nickname;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别,0 保密,1 男,2 女
     */
    private Integer gender = 0;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    /**
     * 密码重置时间
     */
    private Date pwdResetTime;
    /**
     * 是否系统用户
     */
    @Column(name = "`is_system`", columnDefinition = "bit(1) default 0")
    private Boolean system = false;
    /**
     * 状态
     */
    @Column(name = "`is_enabled`", columnDefinition = "bit(1) default 1")
    private Boolean enabled = true;
}
