package com.hb0730.sys.tenant.rpc;

import com.hb0730.base.conf.rpc.server.BaseServerRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.rpc.sys.tenant.domain.TenantPermissionDto;
import com.hb0730.rpc.sys.tenant.domain.TenantPermissionSmallDto;
import com.hb0730.rpc.sys.tenant.domain.query.PermissionQuery;
import com.hb0730.rpc.sys.tenant.service.TenantPermissionRpcService;
import com.hb0730.sys.tenant.domain.TenantPermission;
import com.hb0730.sys.tenant.rpc.mapstruct.TenantPermissionMapper;
import com.hb0730.sys.tenant.rpc.mapstruct.TenantPermissionSmallMapper;
import com.hb0730.sys.tenant.service.ITenantPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/27
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TenantPermissionRpcServiceImpl extends BaseServerRpcService<TenantPermissionRpcService> implements TenantPermissionRpcService {
    private final TenantPermissionMapper permissionMapper;
    private final TenantPermissionSmallMapper permissionSmallMapper;

    private final ITenantPermissionService permissionService;

    @Override
    public JR<List<TenantPermissionDto>> listDefaultRootQueryOrderRank(PermissionQuery query) {
        List<TenantPermission> permissions = permissionService.listDefaultRootQueryOrderRank(query);
        List<TenantPermissionDto> res = permissionMapper.toDtoList(permissions);
        return JR.okData(res);
    }

    @Override
    public JR<List<TenantPermissionDto>> findAllEnabled() {
        List<TenantPermission> permissions = permissionService.findByEnabledTrueOrderByRank();
        List<TenantPermissionDto> res = permissionMapper.toDtoList(permissions);
        return JR.okData(res);
    }

    @Override
    public JR<String> save(TenantPermissionSmallDto dto) {
        TenantPermission entity = permissionSmallMapper.toEntity(dto);
        permissionService.add(entity);
        return JR.ok();
    }

    @Override
    public JR<String> updateById(TenantPermissionSmallDto dto) {
        TenantPermission entity = permissionSmallMapper.toEntity(dto);
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
