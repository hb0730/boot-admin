package com.hb0730.modules.basic.rpcservice;

import com.hb0730.base.conf.rpc.client.BaseRemoteRpcService;
import com.hb0730.commons.JR;
import com.hb0730.rpc.basic.domain.BasOrgDto;
import com.hb0730.rpc.basic.domain.query.BasOrgQuery;
import com.hb0730.rpc.basic.service.BasOrgRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
public class BasOrgRemoteRpcService extends BaseRemoteRpcService<BasOrgRpcService> implements BasOrgRpcService {
    @Override
    public JR<String> checkOrgExpiredForLogin(String orgId) {
        return getRpcService().checkOrgExpiredForLogin(orgId);
    }

    @Override
    public JR<List<BasOrgDto>> listDefaultRootQuery(BasOrgQuery query) {
        return getRpcService().listDefaultRootQuery(query);
    }

    @Override
    public JR<List<BasOrgDto>> list(BasOrgQuery query) {
        return getRpcService().list(query);
    }

    @Override
    public JR<String> save(BasOrgDto dto) {
        return getRpcService().save(dto);
    }

    @Override
    public JR<String> updateById(BasOrgDto dto) {
        return getRpcService().updateById(dto);
    }
}
