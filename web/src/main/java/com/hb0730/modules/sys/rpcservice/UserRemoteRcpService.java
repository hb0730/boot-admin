package com.hb0730.modules.sys.rpcservice;

import com.hb0730.base.conf.rpc.client.ClientRemoteRpcService;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.rpc.sys.system.domain.UserDto;
import com.hb0730.rpc.sys.system.domain.UserRestPwdDto;
import com.hb0730.rpc.sys.system.domain.query.UserQuery;
import com.hb0730.rpc.sys.system.service.UserRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
@Service
@Slf4j
public class UserRemoteRcpService extends ClientRemoteRpcService<UserRpcService> implements UserRpcService {
    @Override
    public JR<UserDto> findByUsername(String username) {
        return this.getRpcService().findByUsername(username);
    }


    @Override
    public JR<UserDto> findById(Long id) {
        return getRpcService().findById(id);
    }

    @Override
    public JR<String> changeLastLoginTime(String username) {
        return getRpcService().changeLastLoginTime(username);
    }

    @Override
    public JR<List<UserDto>> findByMenuId(Long menuId) {
        return getRpcService().findByMenuId(menuId);
    }

    @Override
    public JR<List<UserDto>> findByRoleId(Long roleId) {
        return getRpcService().findByRoleId(roleId);
    }

    @Override
    public JR<JsfPage<UserDto>> page(UserQuery query) {
        return getRpcService().page(query);
    }

    @Override
    public JR<Boolean> existsByUsernameAndIdNot(String username, Long id) {
        return getRpcService().existsByUsernameAndIdNot(username, id);
    }

    @Override
    public JR<List<UserDto>> list(UserQuery query) {
        return getRpcService().list(query);
    }

    @Override
    public JR<String> save(UserDto dto) {
        return UserRpcService.super.save(dto);
    }

    @Override
    public JR<String> updateById(UserDto dto) {
        return UserRpcService.super.updateById(dto);
    }

    @Override
    public JR<String> deleteById(Long id) {
        return UserRpcService.super.deleteById(id);
    }

    @Override
    public JR<String> resetPassword(UserRestPwdDto dto) {
        return UserRpcService.super.resetPassword(dto);
    }
}
