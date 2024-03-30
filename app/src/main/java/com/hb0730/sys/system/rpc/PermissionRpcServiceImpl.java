package com.hb0730.sys.system.rpc;

import cn.hutool.core.collection.CollectionUtil;
import com.hb0730.base.conf.server.BaseServerRpcService;
import com.hb0730.commons.JR;
import com.hb0730.rpc.sys.system.domain.PermissionDto;
import com.hb0730.rpc.sys.system.domain.PermissionSaveDto;
import com.hb0730.rpc.sys.system.domain.query.PermissionQuery;
import com.hb0730.rpc.sys.system.service.PermissionRpcService;
import com.hb0730.sys.system.domain.SysPermission;
import com.hb0730.sys.system.domain.SysRole;
import com.hb0730.sys.system.rpc.cache.UserMenusCache;
import com.hb0730.sys.system.rpc.mapstruct.PermissionMapper;
import com.hb0730.sys.system.rpc.mapstruct.PermissionSaveMapper;
import com.hb0730.sys.system.service.IPermissionService;
import com.hb0730.sys.system.service.IRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionRpcServiceImpl extends BaseServerRpcService<PermissionRpcService> implements PermissionRpcService {
    private final PermissionMapper permissionMapper;
    private final PermissionSaveMapper permissionSaveMapper;
    private final UserMenusCache userMenusCache;

    private final IPermissionService permissionService;
    private final IRoleService roleService;


    @Override
    public JR<List<PermissionDto>> findByRoleIds(List<Long> roleId) {
        List<SysPermission> permissions = permissionService.getByRoleIdsIn(roleId);
        List<PermissionDto> res = permissionMapper.toDtoList(permissions);
        return JR.okData(res);
    }

    @Override
    public JR<List<PermissionDto>> findByUserId(Long userId) {
        List<PermissionDto> userRoutes = userMenusCache.getUserRoutes(userId);
        if (CollectionUtil.isNotEmpty(userRoutes)) {
            return JR.okData(userRoutes);
        }
        List<SysRole> roles = roleService.getByUserId(userId);
        if (CollectionUtil.isNotEmpty(roles)) {
            List<Long> roleIds = roles.stream().map(SysRole::getId).toList();
            JR<List<PermissionDto>> jr = findByRoleIds(roleIds);
            if (jr.isSuccess()) {
                userRoutes = jr.getResult();
                userMenusCache.putUserRoutes(userId, userRoutes);
            }
            return jr;
        }
        return JR.ok();
    }

    @Override
    public JR<String> clearUserRouteCache(Long userId) {
        userMenusCache.clearUserRoutes(userId);
        return JR.ok();
    }

    @Override
    public JR<List<PermissionDto>> listDefaultRootQueryOrderRank(PermissionQuery query) {
        List<SysPermission> permissions = permissionService.listDefaultRootQueryOrderRank(query);
        List<PermissionDto> res = permissionMapper.toDtoList(permissions);
        return JR.okData(res);
    }

    @Override
    public JR<List<PermissionDto>> findAllEnabled() {
        List<SysPermission> permissions = permissionService.findByEnabledTrueOrderByRank();
        List<PermissionDto> res = permissionMapper.toDtoList(permissions);
        return JR.okData(res);
    }

    @Override
    public JR<String> add(PermissionSaveDto dto) {
        SysPermission entity = permissionSaveMapper.toEntity(dto);
        permissionService.add(entity);
        return JR.ok();
    }

    @Override
    public JR<String> updateById(PermissionSaveDto dto) {
        SysPermission entity = permissionSaveMapper.toEntity(dto);
        permissionService.updateById(entity);
        return JR.ok();
    }

    @Override
    public JR<String> deleteById(Long id) {
        boolean exits = permissionService.hasChildren(id);
        if (exits) {
            return JR.fail("存在子级，不能删除");
        }
        permissionService.deleteById(id);
        return JR.ok();
    }
}
