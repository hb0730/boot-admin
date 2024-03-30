package com.hb0730.sys.tenant.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "bas_role_permission")
public class TenantRolePermission implements Serializable {
    @Id
    @Column(name = "role_id")
    private String roleId;
    @Id
    @Column(name = "permission_id")
    private Long permissionId;
}
