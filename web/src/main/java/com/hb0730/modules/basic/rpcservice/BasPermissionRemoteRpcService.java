package com.hb0730.modules.basic.rpcservice;

import com.hb0730.base.conf.client.ClientRemoteRpcService;
import com.hb0730.commons.JR;
import com.hb0730.rpc.basic.domain.BasPermissionDto;
import com.hb0730.rpc.basic.domain.query.BasPermissionQuery;
import com.hb0730.rpc.basic.service.BasPermissionRpcService;
import com.hb0730.rpc.sys.system.domain.PermissionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Service
@Slf4j
public class BasPermissionRemoteRpcService extends ClientRemoteRpcService<BasPermissionRpcService> implements BasPermissionRpcService {
    @Override
    public JR<List<BasPermissionDto>> findByRoleIds(List<String> roleIds) {
        return getRpcService().findByRoleIds(roleIds);
    }

    @Override
    public JR<List<PermissionDto>> findByUserId(String userId, String sysCode) {
        return getRpcService().findByUserId(userId, sysCode);
    }

    @Override
    public JR<String> clearUserRoutesCache(String userId, String sysCode) {
        return getRpcService().clearUserRoutesCache(userId, sysCode);
    }

    @Override
    public JR<List<BasPermissionDto>> list(BasPermissionQuery query) {
        return getRpcService().list(query);
    }
}
