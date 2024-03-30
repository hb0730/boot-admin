package com.hb0730.sys.system.rpc;

import com.hb0730.base.conf.server.BaseServerRpcService;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.rpc.sys.system.domain.UserDto;
import com.hb0730.rpc.sys.system.domain.UserRestPwdDto;
import com.hb0730.rpc.sys.system.domain.query.UserQuery;
import com.hb0730.rpc.sys.system.service.UserRpcService;
import com.hb0730.sys.system.domain.SysUser;
import com.hb0730.sys.system.rpc.mapstruct.UserMapper;
import com.hb0730.sys.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserRpcServiceImpl extends BaseServerRpcService<UserRpcService> implements UserRpcService {
    private final UserMapper userMapper;


    private final IUserService userService;

    @Override
    public JR<UserDto> findByUsername(String username) {
        SysUser user = userService.findByUsername(username);
        UserDto re = userMapper.toDto(user);
        return JR.okData(re);
    }

    @Override
    public JR<String> changeLastLoginTime(String username) {
        userService.changeLastLoginTimeByUsername(username);
        return JR.ok();
    }

    @Override
    public JR<List<UserDto>> findByMenuId(Long menuId) {
        List<SysUser> users = userService.findByMenuId(menuId);
        List<UserDto> res = userMapper.toDtoList(users);
        return JR.okData(res);
    }

    @Override
    public JR<List<UserDto>> findByRoleId(Long roleId) {
        List<SysUser> users = userService.findByRoleId(roleId);
        List<UserDto> res = userMapper.toDtoList(users);
        return JR.okData(res);
    }

    @Override
    public JR<JsfPage<UserDto>> page(UserQuery query) {
        Page<SysUser> page = userService.page(query);
        List<UserDto> dataList = userMapper.toDtoList(page.getContent());
        JsfPage<UserDto> res = JsfPage.of(page, dataList);
        return JR.okData(res);

    }

    @Override
    public JR<Boolean> existsByUsernameAndIdNot(String username, Long id) {
        return JR.okData(userService.existsByUsernameAndIdNot(username, id));
    }

    @Override
    public JR<List<UserDto>> list(UserQuery query) {
        List<SysUser> list = userService.list(query);
        List<UserDto> res = userMapper.toDtoList(list);
        return JR.okData(res);
    }


    @Override
    public JR<String> save(UserDto dto) {
        SysUser entity = userMapper.toEntity(dto);
        userService.save(entity);
        return JR.ok();
    }

    @Override
    public JR<String> updateById(UserDto dto) {
        if (dto.getId() == null) {
            return JR.fail("id不能为空");
        }
        SysUser entity = userMapper.toEntity(dto);
        userService.updateById(entity);
        return JR.ok();
    }

    @Override
    public JR<String> deleteById(Long id) {
        userService.deleteById(id);
        return JR.ok();
    }

    @Override
    public JR<String> resetPassword(UserRestPwdDto dto) {
        if (dto.getId() == null) {
            return JR.fail("id不能为空");
        }
        if (StrUtil.isBlank(dto.getPassword())) {
            return JR.fail("密码不能为空");
        }
        userService.restPassword(dto.getId(), dto.getPassword(), dto.getOperator());
        return JR.ok();
    }
}
