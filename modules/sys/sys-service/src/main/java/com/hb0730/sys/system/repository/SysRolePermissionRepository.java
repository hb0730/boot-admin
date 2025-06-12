package com.hb0730.sys.system.repository;

import com.hb0730.core.repository.BasicRepository;
import com.hb0730.sys.system.model.entity.SysRolePermission;
import com.hb0730.sys.system.repository.mapper.SysRolePermissionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/5/21
 */
@Service
@Slf4j
public class SysRolePermissionRepository extends BasicRepository<String, SysRolePermission, SysRolePermissionMapper> {

    /**
     * 根据角色ID删除
     *
     * @param roleId 角色ID
     * @return 是否成功
     */
    public boolean removeByRoleId(String roleId) {
        return lambdaUpdate().eq(SysRolePermission::getRoleId, roleId).remove();
    }

    /**
     * 根据角色ID查询
     *
     * @param roleId 角色ID
     * @return 列表
     */
    public Optional<List<SysRolePermission>> listByRoleId(String roleId) {
        return baseMapper.of(
                query -> query.eq(SysRolePermission::getRoleId, roleId)
        ).listOptional();
    }

    /**
     * 根据角色ID查询
     *
     * @param roleIds 角色ID
     * @return 列表
     */
    public Optional<List<SysRolePermission>> listByRoleIds(List<String> roleIds) {
        return baseMapper.of(
                query -> query.in(SysRolePermission::getRoleId, roleIds)
        ).listOptional();
    }
}
