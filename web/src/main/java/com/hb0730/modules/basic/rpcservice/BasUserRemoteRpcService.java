package com.hb0730.modules.basic.rpcservice;

import com.hb0730.base.conf.client.ClientRemoteRpcService;
import com.hb0730.commons.JR;
import com.hb0730.rpc.basic.domain.BasUserDto;
import com.hb0730.rpc.basic.service.BasUserRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
public class BasUserRemoteRpcService extends ClientRemoteRpcService<BasUserRpcService> implements BasUserRpcService {
    @Override
    public JR<String> getSysCodeByUsername(String username) {
        return getRpcService().getSysCodeByUsername(username);
    }

    @Override
    public JR<BasUserDto> findByUsername(String username) {
        return getRpcService().findByUsername(username);
    }

    @Override
    public JR<String> updateLastLoginTime(String username) {
        return getRpcService().updateLastLoginTime(username);
    }
}
