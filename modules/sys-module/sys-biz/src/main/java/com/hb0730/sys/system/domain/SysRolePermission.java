package com.hb0730.sys.system.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Getter
@Setter
@Entity
@Table(name = "sys_role_permission")
public class SysRolePermission implements Serializable {
    @Id
    @Column(name = "role_id")
    private Integer roleId;
    @Id
    @Column(name = "permission_id")
    private Integer permissionId;

}