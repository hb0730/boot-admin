package com.hb0730.sys.system.rpcservice;

import com.hb0730.biz.dto.sys.system.PermissionDto;
import com.hb0730.biz.dto.sys.system.PermissionSaveDto;
import com.hb0730.commons.JR;
import com.hb0730.conf.rpc.remote.BaseRemoteService;
import com.hb0730.listener.system.UserRolePermissionListener;
import com.hb0730.sys.bean.PermissionQuery;
import com.hb0730.sys.service.PermissionRpcService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单与权限 rpc
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/27
 */
@Service
@RequiredArgsConstructor
public class PermissionRemoteRpcService extends BaseRemoteService<PermissionRpcService> implements PermissionRpcService {
    private final ApplicationContext applicationContext;

    @Override
    public JR<List<PermissionDto>> findByUserId(Integer userId) {
        return getRpcService().findByUserId(userId);
    }

    @Override
    public JR<List<PermissionDto>> findAllEnabled() {
        return getRpcService().findAllEnabled();
    }

    @Override
    public JR<List<PermissionDto>> listDefaultRootQueryOrderRank(PermissionQuery query) {
        return getRpcService().listDefaultRootQueryOrderRank(query);
    }

    @Override
    public JR<String> clearUserPermissionCache(Integer userId) {
        return getRpcService().clearUserPermissionCache(userId);
    }

    @Override
    public JR<String> add(PermissionSaveDto dto) {
        return getRpcService().add(dto);
    }

    @Override
    public JR<String> updateById(PermissionSaveDto dto) {
        JR<String> jr = getRpcService().updateById(dto);
        if (jr.isSuccess()) {
            applicationContext.publishEvent(new UserRolePermissionListener.PermissionChangeEvent(this, dto.getId()));
        }
        return jr;
    }

    @Override
    public JR<String> delete(Integer id) {
        JR<String> res = getRpcService().delete(id);
        if (res.isSuccess()) {
            applicationContext.publishEvent(new UserRolePermissionListener.PermissionChangeEvent(this, id));
        }
        return res;
    }
}
