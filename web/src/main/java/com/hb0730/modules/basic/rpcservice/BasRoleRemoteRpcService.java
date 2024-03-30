package com.hb0730.modules.basic.rpcservice;

import com.hb0730.base.conf.client.ClientRemoteRpcService;
import com.hb0730.commons.JR;
import com.hb0730.rpc.basic.domain.BasRoleDto;
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
public class BasRoleRemoteRpcService extends ClientRemoteRpcService<BasRoleRpcService> implements BasRoleRpcService {
    @Override
    public JR<List<BasRoleDto>> findByUserId(String userId) {
        return getRpcService().findByUserId(userId);
    }
}
