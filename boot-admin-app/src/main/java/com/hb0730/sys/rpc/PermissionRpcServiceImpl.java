package com.hb0730.sys.rpc;

import cn.hutool.core.collection.CollectionUtil;
import com.hb0730.biz.dto.sys.system.PermissionDto;
import com.hb0730.biz.dto.sys.system.PermissionSaveDto;
import com.hb0730.biz.entity.system.Permission;
import com.hb0730.biz.entity.system.Role;
import com.hb0730.commons.JR;
import com.hb0730.conf.rpc.rpc.BaseRpcService;
import com.hb0730.sys.bean.PermissionQuery;
import com.hb0730.sys.rpc.cache.UserRoleMenusCache;
import com.hb0730.sys.rpc.mapstruct.PermissionMapper;
import com.hb0730.sys.rpc.mapstruct.PermissionSaveMapper;
import com.hb0730.sys.service.IPermissionService;
import com.hb0730.sys.service.IRoleService;
import com.hb0730.sys.service.PermissionRpcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/27
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class PermissionRpcServiceImpl extends BaseRpcService<PermissionRpcService> implements PermissionRpcService {
    private final PermissionMapper permissionMapper;
    private final PermissionSaveMapper permissionSaveMapper;
    private final UserRoleMenusCache userRoleMenusCache;

    private final IPermissionService permissionService;
    private final IRoleService roleService;

    @Override
    public JR<List<PermissionDto>> findByUserId(Integer userId) {
        List<PermissionDto> routes = userRoleMenusCache.getUserRoutes(userId);
        if (CollectionUtil.isEmpty(routes)) {
            // 是否为超级管理员
            List<Role> roles = roleService.findByUserId(userId);
            if (CollectionUtil.isEmpty(roles)) {
                return JR.ok();
            }
            List<Integer> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
            List<Permission> permissions = permissionService.findByRoleIds(roleIds);
            routes = permissionMapper.toDtoList(permissions);
        }
        userRoleMenusCache.putUserRoutes(userId, routes);
        return JR.okData(routes);
    }

    @Override
    public JR<List<PermissionDto>> findAllEnabled() {
        List<Permission> permissions = permissionService.findByEnabledTrueOrderByRank();
        List<PermissionDto> res = permissionMapper.toDtoList(permissions);
        return JR.okData(res);
    }

    @Override
    public JR<List<PermissionDto>> listDefaultRootQueryOrderRank(PermissionQuery query) {
        List<Permission> permissions = permissionService.listDefaultRootQueryOrderRank(query);
        List<PermissionDto> res = permissionMapper.toDtoList(permissions);
        return JR.okData(res);
    }

    @Override
    public JR<String> clearUserPermissionCache(Integer userId) {
        userRoleMenusCache.clearUserRoutes(userId);
        return JR.ok();
    }

    @Override
    public JR<String> add(PermissionSaveDto dto) {
        Permission entity = permissionSaveMapper.toEntity(dto);
        permissionService.add(entity);
        return JR.ok();
    }

    @Override
    public JR<String> updateById(PermissionSaveDto dto) {
        Permission entity = permissionSaveMapper.toEntity(dto);
        permissionService.updateById(entity);
        return JR.ok();
    }

    @Override
    public JR<String> delete(Integer id) {
        boolean exits = permissionService.hasChildren(id);
        if (exits) {
            return JR.fail("存在子级，不能删除");
        }
        permissionService.delete(id);
        return JR.ok();
    }
}
