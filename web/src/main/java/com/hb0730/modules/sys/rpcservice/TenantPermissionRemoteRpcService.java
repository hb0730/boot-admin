package com.hb0730.modules.sys.rpcservice;

import com.hb0730.base.conf.rpc.client.BaseRemoteRpcService;
import com.hb0730.common.api.JR;
import com.hb0730.rpc.sys.tenant.domain.TenantPermissionDto;
import com.hb0730.rpc.sys.tenant.domain.TenantPermissionSmallDto;
import com.hb0730.rpc.sys.tenant.domain.query.PermissionQuery;
import com.hb0730.rpc.sys.tenant.service.TenantPermissionRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/28
 */
@Service
@Slf4j
public class TenantPermissionRemoteRpcService extends BaseRemoteRpcService<TenantPermissionRpcService> implements TenantPermissionRpcService {
    @Override
    public JR<List<TenantPermissionDto>> listDefaultRootQueryOrderRank(PermissionQuery query) {
        return getRpcService().listDefaultRootQueryOrderRank(query);
    }

    @Override
    public JR<List<TenantPermissionDto>> findAllEnabled() {
        return getRpcService().findAllEnabled();
    }

    @Override
    public JR<String> save(TenantPermissionSmallDto dto) {
        return getRpcService().save(dto);
    }

    @Override
    public JR<String> updateById(TenantPermissionSmallDto dto) {
        return getRpcService().updateById(dto);
    }

    @Override
    public JR<String> deleteById(Long id) {
        return getRpcService().deleteById(id);
    }
}
