package com.hb0730.sys.system.rpc;

import com.hb0730.base.conf.rpc.server.BaseServerRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.sys.system.domain.RoleDto;
import com.hb0730.rpc.sys.system.domain.query.RoleQuery;
import com.hb0730.rpc.sys.system.service.RoleRpcService;
import com.hb0730.sys.system.domain.SysRole;
import com.hb0730.sys.system.rpc.mapstruct.RoleMapper;
import com.hb0730.sys.system.service.IRoleService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RoleRpcServiceImpl extends BaseServerRpcService<RoleRpcService> implements RoleRpcService {
    private final RoleMapper roleMapper;

    public final IRoleService roleService;

    @Override
    public JR<List<RoleDto>> findAllEnabled() {
        List<SysRole> roles = roleService.findAllEnabled();
        List<RoleDto> res = roleMapper.toDtoList(roles);
        return JR.okData(res);
    }

    @Override
    public JR<List<RoleDto>> findByUserId(Long userId) {
        List<SysRole> roles = roleService.getByUserId(userId);
        List<RoleDto> res = roleMapper.toDtoList(roles);
        return JR.okData(res);
    }

    @Override
    public JR<JsfPage<RoleDto>> page(RoleQuery query) {
        Page<SysRole> page = roleService.page(query);
        List<RoleDto> data = roleMapper.toDtoList(page.getContent());
        JsfPage<RoleDto> re = JsfPage.of(page, data);
        return JR.okData(re);
    }

    @Override
    public JR<List<RoleDto>> list(RoleQuery query) {
        List<SysRole> roles = roleService.list(query);
        List<RoleDto> res = roleMapper.toDtoList(roles);
        return JR.okData(res);
    }

    @Override
    public JR<Boolean> existsByCode(String code, @Nullable Long id) {
        return JR.okData(roleService.existsByCode(code, id));
    }

    @Override
    public JR<String> add(RoleDto roleDto) {
        SysRole entity = roleMapper.toEntity(roleDto);
        roleService.save(entity);
        return JR.ok();
    }

    @Override
    public JR<String> updateById(RoleDto roleDto) {
        SysRole entity = roleMapper.toEntity(roleDto);
        roleService.updateById(entity);
        return JR.ok();
    }

    @Override
    public JR<String> deleteById(Long id) {
        roleService.deleteById(id);
        return JR.ok();
    }

    @Override
    public JR<String> assignPermission(Long roleId, List<Long> permissionIds) {
        // 清空菜单与权限
        roleService.assignPermission(roleId, permissionIds);
        return JR.ok();
    }
}
