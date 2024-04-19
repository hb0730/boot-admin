package com.hb0730.sys.system.domain;

import cn.hutool.core.collection.CollectionUtil;
import com.hb0730.base.jpa.core.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 管理端 角色
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "sys_role")
public class SysRole extends BaseEntity {

    /**
     * 角色名称
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 权限
     */
    @ManyToMany(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinTable(
            name = "sys_role_permission",
            joinColumns = @jakarta.persistence.JoinColumn(name = "role_id"),
            inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "permission_id")
    )
    private List<SysPermission> permissions;
    /**
     * 用户
     */
    @ManyToMany(mappedBy = "roles")
    public List<SysUser> users;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @Column(name = "`name`")
    private String name;
    /**
     * 角色标识
     */
    @NotBlank(message = "角色标识不能为空")
    @Column(name = "`code`")
    private String code;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否启用
     */
    @Column(name = "is_enabled", columnDefinition = "bit(1) default 1")
    private Boolean enabled;


    /**
     * 获取权限id
     *
     * @return 权限id
     */
    public List<Long> getPermissionIds() {
        if (CollectionUtil.isEmpty(permissions)) {
            return null;
        }
        return CollectionUtil.newArrayList(permissions.stream().map(SysPermission::getId).iterator());
    }
}
