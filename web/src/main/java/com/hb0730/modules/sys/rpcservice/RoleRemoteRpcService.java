package com.hb0730.modules.sys.rpcservice;

import com.hb0730.base.conf.rpc.client.BaseRemoteRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.sys.system.domain.RoleDto;
import com.hb0730.rpc.sys.system.domain.query.RoleQuery;
import com.hb0730.rpc.sys.system.service.RoleRpcService;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
@Service
@Slf4j
public class RoleRemoteRpcService extends BaseRemoteRpcService<RoleRpcService> implements RoleRpcService {
    @Override
    protected String getAppName() {
        return "sys";
    }

    @Override
    public JR<List<RoleDto>> findByUserId(Long userId) {
        return getRpcService().findByUserId(userId);
    }


    @Override
    public JR<List<RoleDto>> findAllEnabled() {
        return getRpcService().findAllEnabled();
    }

    @Override
    public JR<JsfPage<RoleDto>> page(RoleQuery query) {
        return getRpcService().page(query);
    }

    @Override
    public JR<List<RoleDto>> list(RoleQuery query) {
        return getRpcService().list(query);
    }

    @Override
    public JR<Boolean> existsByCode(String code, @Nullable Long id) {
        return getRpcService().existsByCode(code, id);
    }

    @Override
    public JR<String> add(RoleDto roleDto) {
        return getRpcService().add(roleDto);
    }

    @Override
    public JR<String> updateById(RoleDto roleDto) {
        return getRpcService().updateById(roleDto);
    }

    @Override
    public JR<String> deleteById(Long id) {
        return getRpcService().deleteById(id);
    }

    @Override
    public JR<String> assignPermission(Long roleId, List<Long> permissionIds) {
        return getRpcService().assignPermission(roleId, permissionIds);
    }
}
