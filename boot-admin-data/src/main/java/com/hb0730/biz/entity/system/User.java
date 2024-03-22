package com.hb0730.biz.entity.system;

import com.hb0730.data.entity.DomainEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 用户-TABLE
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/21
 */
@Getter
@Setter
@Entity
@Table(name = "sys_user")
public class User extends DomainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 角色
     */
    @ManyToMany(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinTable(name = "sys_user_role",
            joinColumns = @jakarta.persistence.JoinColumn(name = "user_id"),
            inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "role_id"))
    private List<Role> roles;
    /**
     * 部门
     */
    @OneToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;
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
     * 用户性别: 0 保密 1男,2女
     */
    private Integer gender;
    /**
     * 是否管理员
     */
    @Column(name = "`is_admin`", columnDefinition = "tinyint(1) default 0")
    private Boolean admin;
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
    @Column(name = "`is_enabled`", columnDefinition = "tinyint(1) default 1")
    private Boolean enabled;
}
