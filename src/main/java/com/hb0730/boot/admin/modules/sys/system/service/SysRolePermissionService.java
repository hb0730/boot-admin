package com.hb0730.boot.admin.modules.sys.system.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hb0730.boot.admin.core.service.BaseServiceImpl;
import com.hb0730.boot.admin.modules.sys.system.mapper.SysRolePermissionMapper;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysRolePermission;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/3/2
 */
@Service
@Slf4j
public class SysRolePermissionService extends BaseServiceImpl<SysRolePermissionMapper, SysRolePermission> {


    /**
     * 获取角色对应的权限
     *
     * @param id 角色ID
     * @return 权限ID
     */
    public Optional<List<String>> findPermissionIdByRole(String id) {
        LambdaQueryWrapper<SysRolePermission> queryWrapper = Wrappers.lambdaQuery(SysRolePermission.class)
            .eq(SysRolePermission::getRoleId, id);
        return Optional.of(this.baseMapper.selectList(queryWrapper).stream().map(SysRolePermission::getPermissionId).collect(Collectors.toList()));
    }

    /**
     * 更新角色权
     *
     * @param id            id
     * @param permissionIds 权限集合，如果权限为空，则该角色没有权限
     * @return .
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePermissionByRoles(@Null String id, @Nullable List<SysRolePermission> permissionIds) {
        LambdaQueryWrapper<SysRolePermission> queryWrapper = Wrappers.lambdaQuery(SysRolePermission.class)
            .eq(SysRolePermission::getRoleId, id);
        this.baseMapper.delete(queryWrapper);
        if (CollectionUtil.isNotEmpty(permissionIds)) {
            saveBatch(permissionIds);
        }
        return true;
    }

}
