package com.hb0730.boot.admin.project.system.role.model.dto;

import com.hb0730.boot.admin.domain.model.InputConverter;
import com.hb0730.boot.admin.project.system.role.model.entity.RoleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 角色数据范围
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class RoleExtDTO extends RoleDTO implements InputConverter<RoleEntity> {
    private List<Long> deptIds;
    private List<Long> permissionIds;
}
