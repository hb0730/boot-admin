package com.hb0730.sys.tenant.domain;

import com.hb0730.base.jpa.core.domain.TenantBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "bas_role")
public class TenantRole extends TenantBaseEntity {

    @Id
    private String id;
    /**
     * 菜单与权限
     */
    @ManyToMany(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinTable(name = "bas_role_permission",
            joinColumns = @jakarta.persistence.JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "permission_id", referencedColumnName = "id"))
    @OrderBy("rank")
    private List<TenantPermission> permissions;
    /**
     * 组织
     */
    @OneToOne
    @JoinColumn(name = "org_id")
    private TenantOrg org;
    /**
     * 用户
     */
    @ManyToMany(mappedBy = "roles")
    private List<TenantUser> users;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否系统角色
     */
    @Column(name = "`is_system`", columnDefinition = "bit(1) default 0")
    private Boolean system = false;
    /**
     * 是否启用
     */
    @Column(name = "`is_enabled`", columnDefinition = "bit(1) default 1")
    private Boolean enabled = true;
}
