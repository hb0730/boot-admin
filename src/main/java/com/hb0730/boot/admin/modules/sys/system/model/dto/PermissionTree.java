package com.hb0730.boot.admin.modules.sys.system.model.dto;

import com.hb0730.boot.admin.modules.sys.system.model.entity.SysPermission;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限树
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/6
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class PermissionTree extends SysPermission {
    private List<PermissionTree> children = new ArrayList<>();
}
