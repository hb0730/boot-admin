package com.hb0730.sys.system.rpcservice;

import com.hb0730.biz.dto.sys.system.RoleDto;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.conf.rpc.remote.BaseRemoteService;
import com.hb0730.listener.system.UserRolePermissionListener;
import com.hb0730.sys.bean.RoleQuery;
import com.hb0730.sys.service.RoleRpcService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/28
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RoleRemoteRpcService extends BaseRemoteService<RoleRpcService> implements RoleRpcService {
    private final ApplicationContext applicationContext;

    @Override
    public JR<List<RoleDto>> findAllEnabled() {
        return getRpcService().findAllEnabled();
    }

    @Override
    public JR<List<RoleDto>> findByUserId(Integer userId) {
        return getRpcService().findByUserId(userId);
    }

    @Override
    public JR<String> clearUserRoleCache(Integer userId) {
        return getRpcService().clearUserRoleCache(userId);
    }

    @Override
    public JR<JsfPage<RoleDto>> page(RoleQuery query) {
        return getRpcService().page(query);
    }

    @Override
    public JR<List<RoleDto>> list(RoleQuery query) {
        return getRpcService().list(query);
    }

    @Override
    public JR<Boolean> existsByCode(String code, @Nullable Integer id) {
        return getRpcService().existsByCode(code, id);
    }

    @Override
    public JR<String> add(RoleDto roleDto) {
        return getRpcService().add(roleDto);
    }

    @Override
    public JR<String> updateById(RoleDto roleDto) {
        return getRpcService().updateById(roleDto);
    }

    @Override
    public JR<String> deleteById(Integer id) {
        return getRpcService().deleteById(id);
    }

    @Override
    public JR<String> assignPermission(Integer roleId, List<Integer> permissionIds) {
        JR<String> jr = getRpcService().assignPermission(roleId, permissionIds);
        if (jr.isSuccess()) {
            applicationContext.publishEvent(new UserRolePermissionListener.RolePermissionChangeEvent(this, roleId));
        }
        return jr;
    }
}
