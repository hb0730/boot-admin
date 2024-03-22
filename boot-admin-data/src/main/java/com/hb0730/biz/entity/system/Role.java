package com.hb0730.biz.entity.system;

import cn.hutool.core.collection.CollectionUtil;
import com.hb0730.data.entity.DomainEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 角色-TABLE
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/28
 */
@Getter
@Setter
@Entity
@Table(name = "sys_role")
public class Role extends DomainEntity {
    /**
     * 角色名称
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 菜单与权限
     */
    @ManyToMany(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinTable(name = "sys_role_permission",
            joinColumns = @jakarta.persistence.JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "permission_id", referencedColumnName = "id"))
    @OrderBy("rank")
    private List<Permission> permissions;
    /**
     * 用户
     */
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
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
    public List<Integer> getPermissionIds() {
        if (CollectionUtil.isEmpty(permissions)) {
            return null;
        }
        return CollectionUtil.newArrayList(permissions.stream().map(Permission::getId).iterator());
    }
}
