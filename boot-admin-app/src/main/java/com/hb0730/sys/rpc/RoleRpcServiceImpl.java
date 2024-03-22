package com.hb0730.sys.rpc;

import cn.hutool.core.collection.CollectionUtil;
import com.hb0730.biz.dto.sys.system.RoleDto;
import com.hb0730.biz.entity.system.Role;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.conf.rpc.rpc.BaseRpcService;
import com.hb0730.sys.bean.RoleQuery;
import com.hb0730.sys.rpc.cache.UserRoleMenusCache;
import com.hb0730.sys.rpc.mapstruct.RoleMapper;
import com.hb0730.sys.service.IRoleService;
import com.hb0730.sys.service.RoleRpcService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/28
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class RoleRpcServiceImpl extends BaseRpcService<RoleRpcService> implements RoleRpcService {
    private final RoleMapper roleMapper;
    private final IRoleService roleService;
    private final UserRoleMenusCache userRoleMenusCache;

    @Override
    public JR<List<RoleDto>> findAllEnabled() {
        List<Role> roles = roleService.findAllEnabled();
        List<RoleDto> res = roleMapper.toDtoList(roles);
        return JR.okData(res);
    }

    @Override
    public JR<List<RoleDto>> findByUserId(Integer userId) {
        List<RoleDto> userRoles = userRoleMenusCache.getUserRoles(userId);
        if (CollectionUtil.isNotEmpty(userRoles)) {
            return JR.okData(userRoles);
        }
        List<Role> roles = roleService.findByUserId(userId);
        List<RoleDto> res = roleMapper.toDtoList(roles);
        userRoleMenusCache.putUserRoles(userId, res);
        return JR.okData(res);
    }

    @Override
    public JR<String> clearUserRoleCache(Integer userId) {
        userRoleMenusCache.clearUserRoles(userId);
        return JR.ok();
    }

    @Override
    public JR<JsfPage<RoleDto>> page(RoleQuery query) {
        Page<Role> page = roleService.page(query);
        List<RoleDto> data = roleMapper.toDtoList(page.getContent());
        JsfPage<RoleDto> re = JsfPage.of(page, data);
        return JR.okData(re);
    }

    @Override
    public JR<List<RoleDto>> list(RoleQuery query) {
        List<Role> roles = roleService.list(query);
        List<RoleDto> res = roleMapper.toDtoList(roles);
        return JR.okData(res);
    }

    @Override
    public JR<Boolean> existsByCode(String code, @Nullable Integer id) {
        return JR.okData(roleService.existsByCode(code, id));
    }

    @Override
    public JR<String> add(RoleDto roleDto) {
        Role entity = roleMapper.toEntity(roleDto);
        roleService.save(entity);
        return JR.ok();
    }

    @Override
    public JR<String> updateById(RoleDto roleDto) {
        Role entity = roleMapper.toEntity(roleDto);
        roleService.updateById(entity);
        return JR.ok();
    }

    @Override
    public JR<String> deleteById(Integer id) {
        roleService.deleteById(id);
        return JR.ok();
    }

    @Override
    public JR<String> assignPermission(Integer roleId, List<Integer> permissionIds) {
        // 清空菜单与权限
        roleService.assignPermission(roleId, permissionIds);
        return JR.ok();
    }
}
