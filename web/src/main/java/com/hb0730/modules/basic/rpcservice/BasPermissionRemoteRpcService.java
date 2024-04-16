package com.hb0730.modules.basic.rpcservice;

import com.hb0730.base.conf.rpc.client.BaseRemoteRpcService;
import com.hb0730.common.api.JR;
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
public class BasPermissionRemoteRpcService extends BaseRemoteRpcService<BasPermissionRpcService> implements BasPermissionRpcService {
    @Override
    protected String getAppName() {
        return "sys";
    }

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
