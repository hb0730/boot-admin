package com.hb0730.basic.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Data
@Entity
@Table(name = "bas_role_permission")
public class BasRolePermission implements java.io.Serializable {
    @Id
    @Column(name = "role_id")
    private String roleId;
    @Id
    @Column(name = "permission_id")
    private Long permissionId;
}
