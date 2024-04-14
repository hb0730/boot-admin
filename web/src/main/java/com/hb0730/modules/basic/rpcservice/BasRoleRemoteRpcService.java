package com.hb0730.modules.basic.rpcservice;

import com.hb0730.base.conf.rpc.client.BaseRemoteRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.common.api.JsfPage;
import com.hb0730.rpc.basic.domain.BasRoleDto;
import com.hb0730.rpc.basic.domain.query.BasRoleQuery;
import com.hb0730.rpc.basic.service.BasRoleRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
public class BasRoleRemoteRpcService extends BaseRemoteRpcService<BasRoleRpcService> implements BasRoleRpcService {
    @Override
    public JR<List<BasRoleDto>> findByUserId(String userId) {
        return getRpcService().findByUserId(userId);
    }

    @Override
    public JR<Boolean> existsByCode(String code, String sysCode, String id) {
        return getRpcService().existsByCode(code, sysCode, id);
    }

    @Override
    public JR<JsfPage<BasRoleDto>> page(BasRoleQuery query) {
        return getRpcService().page(query);
    }

    @Override
    public JR<List<BasRoleDto>> list(BasRoleQuery query) {
        return getRpcService().list(query);
    }

    @Override
    public JR<String> save(BasRoleDto dto) {
        return getRpcService().save(dto);
    }

    @Override
    public JR<String> updateById(BasRoleDto dto) {
        return getRpcService().updateById(dto);
    }

    @Override
    public JR<String> delete(String id) {
        return getRpcService().delete(id);
    }

    @Override
    public JR<String> grant(String roleId, List<Long> permissionIds) {
        return getRpcService().grant(roleId, permissionIds);
    }
}
