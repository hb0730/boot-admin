package com.hb0730.biz.entity.system;

import com.hb0730.data.entity.DomainEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/28
 */
@Getter
@Setter
@Entity
@Table(name = "sys_role_permission")
public class RolePermission extends DomainEntity {
    @Id
    @Column(name = "role_id")
    private Integer roleId;
    @Id
    @Column(name = "permission_id")
    private Integer permissionId;

}
