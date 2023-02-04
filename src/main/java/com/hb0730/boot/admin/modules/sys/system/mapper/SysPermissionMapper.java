package com.hb0730.boot.admin.modules.sys.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysPermission;
import jakarta.annotation.Nullable;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/4
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 获取权限ID集合 BY 角色ID结合
     *
     * @param roleIds 角色ID集合
     * @return 权限ID集合
     */
    @Nullable
    Set<String> listPermissionIdsByRoleIds(@Param("roleIds") List<String> roleIds);

    /**
     * 获取权限代码根据权限ID
     *
     * @param permissionIds 权限ID
     * @return 权限代码
     */
    Set<String> listPermissionPreByIds(@Param("permissionIds") List<String> permissionIds);
}
