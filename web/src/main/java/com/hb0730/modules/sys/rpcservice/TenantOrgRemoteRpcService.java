package com.hb0730.modules.sys.rpcservice;

import com.hb0730.base.conf.client.ClientRemoteRpcService;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.rpc.sys.tenant.domain.TenantOrgDto;
import com.hb0730.rpc.sys.tenant.domain.TenantSmallDto;
import com.hb0730.rpc.sys.tenant.domain.UpdateTenantOrgConfigDto;
import com.hb0730.rpc.sys.tenant.domain.query.TenantQuery;
import com.hb0730.rpc.sys.tenant.service.TenantOrgRpcService;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/29
 */
@Service
@Slf4j
public class TenantOrgRemoteRpcService extends ClientRemoteRpcService<TenantOrgRpcService> implements TenantOrgRpcService {

    @Override
    public JR<Boolean> existsByCode(String code, @Nullable String id) {
        return getRpcService().existsByCode(code, id);
    }

    @Override
    public JR<JsfPage<TenantOrgDto>> queryTenantOrganization(TenantQuery query) {
        return getRpcService().queryTenantOrganization(query);
    }

    @Override
    public JR<String> saveTenantOrganization(TenantSmallDto dto) {
        return getRpcService().saveTenantOrganization(dto);
    }

    @Override
    public JR<String> updateTenantOrganization(TenantSmallDto dto) {
        return getRpcService().updateTenantOrganization(dto);
    }

    @Override
    public JR<String> resetPassword(String id, String operator) {
        return getRpcService().resetPassword(id, operator);
    }

    @Override
    public JR<String> updateTenantOrgConfig(UpdateTenantOrgConfigDto dto) {
        return getRpcService().updateTenantOrgConfig(dto);
    }
}
