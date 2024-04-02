package com.hb0730.basic.rpc;

import cn.hutool.core.collection.CollectionUtil;
import com.hb0730.base.conf.rpc.server.BaseServerRpcService;
import com.hb0730.basic.domain.BasPermission;
import com.hb0730.basic.domain.BasRole;
import com.hb0730.basic.rpc.cache.BasUserMenusCache;
import com.hb0730.basic.rpc.mapstruct.BasPermissionMapper;
import com.hb0730.basic.service.IBasPermissionService;
import com.hb0730.basic.service.IBasRoleService;
import com.hb0730.commons.JR;
import com.hb0730.rpc.basic.domain.BasPermissionDto;
import com.hb0730.rpc.basic.domain.query.BasPermissionQuery;
import com.hb0730.rpc.basic.service.BasPermissionRpcService;
import com.hb0730.rpc.sys.system.domain.PermissionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BasPermissionRpcServiceImpl extends BaseServerRpcService<BasPermissionRpcService> implements BasPermissionRpcService {
    private final IBasPermissionService basPermissionService;
    private final BasPermissionMapper basPermissionMapper;
    private final IBasRoleService roleService;
    private final BasUserMenusCache basUserMenusCache;

    @Override
    public JR<List<BasPermissionDto>> findByRoleIds(List<String> roleIds) {
        List<BasPermission> permissions = basPermissionService.findByRoleIds(roleIds);
        return JR.okData(basPermissionMapper.toDtoList(permissions));
    }

    @Override
    public JR<List<PermissionDto>> findByUserId(String userId, String sysCode) {
        //从缓存中获取
        List<PermissionDto> list = basUserMenusCache.getUserRoutes(userId, sysCode);
        List<BasRole> roles = roleService.findByUserId(userId);
        if (CollectionUtil.isNotEmpty(roles)) {
            List<String> roleIds = roles.stream().map(BasRole::getId).collect(Collectors.toList());
            List<BasPermission> permissions = basPermissionService.findByRoleIds(roleIds);
            list = basPermissionMapper.toPermissionDtoList(permissions);
            //缓存
            basUserMenusCache.putUserRoutes(userId, sysCode, list);
        }
        return JR.okData(list);
    }

    @Override
    public JR<String> clearUserRoutesCache(String userId, String sysCode) {
        basUserMenusCache.removeUserRoutes(userId, sysCode);
        return JR.ok();
    }

    @Override
    public JR<List<BasPermissionDto>> list(BasPermissionQuery query) {
        List<BasPermission> list = basPermissionService.list(query);
        return JR.okData(basPermissionMapper.toDtoList(list));
    }
}
