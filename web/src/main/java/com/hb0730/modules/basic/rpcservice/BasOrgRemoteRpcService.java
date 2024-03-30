package com.hb0730.modules.basic.rpcservice;

import com.hb0730.base.conf.client.ClientRemoteRpcService;
import com.hb0730.commons.JR;
import com.hb0730.rpc.basic.service.BasOrgRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
public class BasOrgRemoteRpcService extends ClientRemoteRpcService<BasOrgRpcService> implements BasOrgRpcService {
    @Override
    public JR<String> checkOrgExpiredForLogin(String orgId) {
        return getRpcService().checkOrgExpiredForLogin(orgId);
    }
}
