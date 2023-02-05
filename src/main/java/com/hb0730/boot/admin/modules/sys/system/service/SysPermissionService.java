package com.hb0730.boot.admin.modules.sys.system.service;

import com.hb0730.boot.admin.base.core.service.BaseServiceImpl;
import com.hb0730.boot.admin.modules.sys.system.mapper.SysPermissionMapper;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysPermission;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/4
 */
@Service
@Slf4j
public class SysPermissionService extends BaseServiceImpl<SysPermissionMapper, SysPermission> {
    /**
     * 获取权限ID集合 BY 角色ID结合
     *
     * @param roleIds 角色ID集合
     * @return 权限ID集合
     */
    @Nonnull
    public Set<String> listPermissionIdsByRoleIds(@Nonnull List<String> roleIds) {
        Set<String> permissionIds = this.baseMapper.listPermissionIdsByRoleIds(roleIds);
        return permissionIds == null ? Collections.emptySet() : permissionIds;
    }

    /**
     * 根据权限ID获取ID代码
     *
     * @param permissionIds 权限ID
     * @return 权限代码
     */
    @Nonnull
    public Set<String> listPermissionPreByIds(@Nonnull List<String> permissionIds) {
        Set<String> preCodes = this.baseMapper.listPermissionPreByIds(permissionIds);
        return preCodes == null ? Collections.emptySet() : preCodes;
    }

    /**
     * 获取全部的权限信息
     *
     * @return .
     */
    @Nonnull
    public Set<String> allPermissionPre() {
        Set<String> preCodes = this.baseMapper.allPermissionPre();
        return preCodes == null ? Collections.emptySet() : preCodes;
    }
}
