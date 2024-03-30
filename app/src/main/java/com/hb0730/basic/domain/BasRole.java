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

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "bas_role")
public class BasRole extends TenantBaseEntity {
    @Id
    @IdGenerator
    private String id;
    /**
     * 权限
     */
    @ManyToMany
    @JoinTable(name = "bas_role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<BasPermission> permissions;
    /**
     * 用户
     */
    @ManyToMany(mappedBy = "roles")
    private List<BasUser> users;
    /**
     * 组织
     */
    @OneToOne
    @JoinColumn(name = "org_id")
    private BasOrg org;
    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String name;
    /**
     * 角色编码
     */
    @NotBlank(message = "角色编码不能为空")
    private String code;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 是否系统
     */
    @Column(name = "`is_system`", columnDefinition = "bit(1) default 0")
    private Boolean system = false;
    /**
     * 是否启用
     */
    @Column(name = "`is_enabled`", columnDefinition = "bit(1) default 1")
    private Boolean enabled = true;
}
