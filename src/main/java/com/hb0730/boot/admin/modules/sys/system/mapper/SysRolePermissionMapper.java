package com.hb0730.boot.admin.modules.sys.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/3/2
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {

    /**
     * 根据权限ID获取所有的父级ID
     *
     * @param permissionIds .
     * @return .
     */
    List<String> getParentIdByPermissionIds(@Param("permissionIds") List<String> permissionIds);
}
