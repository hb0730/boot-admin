package com.hb0730.modules.sys.rpcservice;

import com.hb0730.base.conf.rpc.client.BaseRemoteRpcService;
import com.hb0730.commons.JR;
import com.hb0730.rpc.sys.system.domain.PermissionDto;
import com.hb0730.rpc.sys.system.domain.PermissionSaveDto;
import com.hb0730.rpc.sys.system.domain.query.PermissionQuery;
import com.hb0730.rpc.sys.system.service.PermissionRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
@Service
@Slf4j
public class PermissionRemoteRpcService extends BaseRemoteRpcService<PermissionRpcService> implements PermissionRpcService {
    @Override
    public JR<List<PermissionDto>> findByRoleIds(List<Long> roleId) {
        return getRpcService().findByRoleIds(roleId);
    }

    @Override
    public JR<List<PermissionDto>> findByUserId(Long userId) {
        return getRpcService().findByUserId(userId);
    }

    @Override
    public JR<String> clearUserRouteCache(Long userId) {
        return getRpcService().clearUserRouteCache(userId);
    }

    @Override
    public JR<List<PermissionDto>> listDefaultRootQueryOrderRank(PermissionQuery query) {
        return getRpcService().listDefaultRootQueryOrderRank(query);
    }

    @Override
    public JR<List<PermissionDto>> findAllEnabled() {
        return getRpcService().findAllEnabled();
    }

    @Override
    public JR<String> add(PermissionSaveDto dto) {
        return getRpcService().add(dto);
    }

    @Override
    public JR<String> updateById(PermissionSaveDto dto) {
        return getRpcService().updateById(dto);
    }

    @Override
    public JR<String> deleteById(Long id) {
        return getRpcService().deleteById(id);
    }
}
