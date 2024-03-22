package com.hb0730.sys.system.rpcservice;

import com.hb0730.biz.dto.sys.system.UserDto;
import com.hb0730.biz.dto.sys.system.UserLoginDto;
import com.hb0730.biz.dto.sys.system.UserRestPwdDto;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.conf.rpc.remote.BaseRemoteService;
import com.hb0730.sys.bean.UserQuery;
import com.hb0730.sys.service.UserRpcService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户 RPC 服务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/21
 */
@Service
public class UserRemoteRpcService extends BaseRemoteService<UserRpcService> implements UserRpcService {
    @Override
    public JR<UserDto> findByUsername(String username) {
        return getRpcService().findByUsername(username);
    }

    @Override
    public JR<UserLoginDto> loginByUsername(String username) {
        return getRpcService().loginByUsername(username);
    }

    @Override
    public JR<String> changeLastLoginTime(String username) {
        return getRpcService().changeLastLoginTime(username);
    }

    @Override
    public JR<List<UserDto>> findByMenuId(Integer menuId) {
        return getRpcService().findByMenuId(menuId);
    }

    @Override
    public JR<List<UserDto>> findByRoleId(Integer roleId) {
        return getRpcService().findByRoleId(roleId);
    }

    @Override
    public JR<JsfPage<UserDto>> page(UserQuery query) {
        return getRpcService().page(query);
    }

    @Override
    public JR<List<UserDto>> list(UserQuery query) {
        return getRpcService().list(query);
    }

    @Override
    public JR<Boolean> existsByUsernameAndIdNot(String username, Integer id) {
        return getRpcService().existsByUsernameAndIdNot(username, id);
    }

    @Override
    public JR<String> save(UserDto dto) {
        return getRpcService().save(dto);
    }

    @Override
    public JR<String> updateById(UserDto dto) {
        return getRpcService().updateById(dto);
    }

    @Override
    public JR<String> deleteById(Integer id) {
        return getRpcService().deleteById(id);
    }

    @Override
    public JR<String> resetPassword(UserRestPwdDto dto) {
        return getRpcService().resetPassword(dto);
    }
}
